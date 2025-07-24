/**
 * @file authService.ts
 * @folder services
 * @author PierreDevC
 * @description Authentication service
 */

import api from '@/lib/api';
import { LoginCredentials, RegisterCredentials, AuthResponse, User } from '@/types/auth';

/**
 * @class AuthService
 * @description Service d'authentification
 * @method login - Méthode pour se connecter
 * @method register - Méthode pour s'inscrire
 * @method getCurrentUser - Méthode pour récupérer l'utilisateur connecté
 * @method refreshToken - Méthode pour rafraîchir le token
 */
export class AuthService {
  /**
   * @description Méthode pour se connecter
   * @param {LoginCredentials} credentials - Les identifiants de connexion
   * @returns {Promise<AuthResponse>} La réponse de l'API
   * @throws {Error} Si l'erreur est détectée
   * @api /user/login
   */
  static async login(credentials: LoginCredentials): Promise<AuthResponse> {
    try {
      const response = await api.post('/user/login', credentials);
      return response.data;
    } catch (error: any) {
      throw new Error(error.response?.data || 'Erreur de connexion');
    }
  }

  /**
   * @description Méthode pour s'inscrire
   * @param {RegisterCredentials} credentials - Les identifiants d'inscription
   * @returns {Promise<void>} La réponse de l'API
   * @throws {Error} Si l'erreur est détectée
   * @api /user/register
   */
  static async register(credentials: RegisterCredentials): Promise<void> {
    try {
      await api.post('/user/register', {
        username: credentials.username,
        email: credentials.email,
        password: credentials.password
      });
    } catch (error: any) {
      throw new Error(error.response?.data || 'Erreur d\'inscription');
    }
  }

  /**
   * @description Méthode pour récupérer l'utilisateur connecté
   * @returns {Promise<User>} L'utilisateur connecté
   * @throws {Error} Si l'erreur est détectée
   * @api /user/me
   */
  static async getCurrentUser(): Promise<User> {
    try {
      const response = await api.get('/user/me');
      return response.data;
    } catch (error: any) {
      throw new Error(error.response?.data || 'Erreur de récupération du profil');
    }
  }

  /**
   * @description Méthode pour rafraîchir le token
   * @returns {Promise<AuthResponse>} La réponse de l'API
   * @throws {Error} Si l'erreur est détectée
   */
  static async refreshToken(): Promise<AuthResponse> {
    try {
      const response = await api.post('/user/refresh');
      return response.data;
    } catch (error: any) {
      throw new Error('Erreur de rafraîchissement du token');
    }
  }
}