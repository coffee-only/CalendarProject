/**
 * @file AuthContext.tsx
 * @folder contexts
 * @author PierreDevC
 * @description Authentication context
 */

'use client';

import React, { createContext, useContext, useEffect, useState, ReactNode } from 'react';
import { useRouter } from 'next/navigation';
import toast from 'react-hot-toast';
import { AuthContextType, User, LoginCredentials, RegisterCredentials } from '@/types/auth';
import { AuthService } from '@/services/authService';
import { setToken, getToken, setUser, getUser, removeToken } from '@/lib/auth';

const AuthContext = createContext<AuthContextType | undefined>(undefined); 

/**
 * @interface AuthProviderProps
 * @description Props pour le provider d'authentification
 * @property {ReactNode} children - Les children du provider
 */
interface AuthProviderProps {
  children: ReactNode;
}

/**
 * @function AuthProvider
 * @description Provider d'authentification
 * @param {AuthProviderProps} props - Les props du provider
 * @returns {React.ReactNode} Le provider d'authentification
 */
export function AuthProvider({ children }: AuthProviderProps) {
  const [user, setUserState] = useState<User | null>(null);
  const [token, setTokenState] = useState<string | null>(null);
  const [isLoading, setIsLoading] = useState(true);
  const router = useRouter();

  /**
   * @description Vérification si le mode développement est activé
   * @type {boolean}
   */
  const isDev = process.env.NEXT_PUBLIC_DEV_MODE === 'true';

  /**
   * @description Vérification si l'utilisateur est authentifié
   * @type {boolean}
   */
  const isAuthenticated = isDev || (!!user && !!token);

  /**
   * @description Initialisation de l'authentification
   * @returns {void}
   */
  useEffect(() => {
    if (isDev) {
      // Fake user pour le mode développement
      const devUser: User = {
        id: 1,
        username: "Développeur",
        email: "dev@calendrier.com"
      };
      setUserState(devUser);
      setTokenState("dev-token");
      setIsLoading(false);
    } else {
      initializeAuth();
    }
  }, []);

  /**
   * @description Initialisation de l'authentification
   * @returns {void}
   */
  const initializeAuth = async () => {
    try {
      const storedToken = getToken();
      const storedUser = getUser();

      if (storedToken && storedUser) {
        setTokenState(storedToken);
        setUserState(storedUser);
        
        // Checker si le token est toujours valide
        try {
          await AuthService.getCurrentUser();
        } catch (error) {
          // Token invalide, clean up
          logout();
        }
      }
    } catch (error) {
      console.error('Erreur d\'initialisation de l\'authentification:', error);
      logout();
    } finally {
      setIsLoading(false);
    }
  };

  /**
   * @description Connexion de l'utilisateur
   * @param {LoginCredentials} credentials - Les identifiants de connexion
   * @returns {Promise<void>} La réponse de l'API
   * @throws {Error} Si l'erreur est détectée
   */
  const login = async (credentials: LoginCredentials) => {
    setIsLoading(true);
    try {
      const response = await AuthService.login(credentials);
      
      setToken(response.token);
      setUser(response.user);
      setTokenState(response.token);
      setUserState(response.user);
      
      toast.success('Connexion réussie !');
      router.push('/dashboard');
    } catch (error: any) {
      toast.error(error.message);
      throw error;
    } finally {
      setIsLoading(false);
    }
  };

  /**
   * @description Inscription de l'utilisateur
   * @param {RegisterCredentials} credentials - Les identifiants d'inscription
   * @returns {Promise<void>} La réponse de l'API
   * @throws {Error} Si l'erreur est détectée
   */
  const register = async (credentials: RegisterCredentials) => {
    setIsLoading(true);
    try {
      await AuthService.register(credentials);
      toast.success('Inscription réussie ! Vous pouvez maintenant vous connecter.');
      router.push('/login');
    } catch (error: any) {
      toast.error(error.message);
      throw error;
    } finally {
      setIsLoading(false);
    }
  };

  /**
   * @description Déconnexion de l'utilisateur
   * @returns {void}
   */
  const logout = () => {
    removeToken();
    setTokenState(null);
    setUserState(null);
    toast.success('Déconnexion réussie');
    router.push('/login');
  };

  /**
   * @description Rafraîchissement de l'utilisateur
   * @returns {Promise<void>} La réponse de l'API
   * @throws {Error} Si l'erreur est détectée
   */
  const refreshUser = async () => {
    try {
      const updatedUser = await AuthService.getCurrentUser();
      setUser(updatedUser);
      setUserState(updatedUser);
    } catch (error) {
      console.error('Erreur de mise à jour du profil:', error);
      logout();
    }
  };

  /**
   * @description Valeur du context
   * @type {AuthContextType}
   */
  const value: AuthContextType = {
    user,
    token,
    isLoading,
    isAuthenticated,
    login,
    register,
    logout,
    refreshUser,
  };

  return (
    <AuthContext.Provider value={value}>
      {children}
    </AuthContext.Provider>
  );
}

/**
 * @function useAuth
 * @description Hook pour utiliser le context d'authentification
 * @returns {AuthContextType} Le context d'authentification
 */
export function useAuth(): AuthContextType {
  const context = useContext(AuthContext);
  if (context === undefined) {
    throw new Error('useAuth doit être utilisé dans un AuthProvider');
  }
  return context;
} 