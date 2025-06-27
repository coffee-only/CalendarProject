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

interface AuthProviderProps {
  children: ReactNode;
}

export function AuthProvider({ children }: AuthProviderProps) {
  const [user, setUserState] = useState<User | null>(null);
  const [token, setTokenState] = useState<string | null>(null);
  const [isLoading, setIsLoading] = useState(true);
  const router = useRouter();

  const isAuthenticated = !!user && !!token;

  useEffect(() => {
    initializeAuth();
  }, []);

  const initializeAuth = async () => {
    try {
      const storedToken = getToken();
      const storedUser = getUser();

      if (storedToken && storedUser) {
        setTokenState(storedToken);
        setUserState(storedUser);
        
        // Vérifier si le token est toujours valide
        try {
          await AuthService.getCurrentUser();
        } catch (error) {
          // Token invalide, nettoyer
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

  const logout = () => {
    removeToken();
    setTokenState(null);
    setUserState(null);
    toast.success('Déconnexion réussie');
    router.push('/login');
  };

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

export function useAuth(): AuthContextType {
  const context = useContext(AuthContext);
  if (context === undefined) {
    throw new Error('useAuth doit être utilisé dans un AuthProvider');
  }
  return context;
} 