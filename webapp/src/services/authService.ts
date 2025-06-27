/**
 * @file authService.ts
 * @folder services
 * @author PierreDevC
 * @description Authentication service
 */

import api from '@/lib/api';
import { LoginCredentials, RegisterCredentials, AuthResponse, User } from '@/types/auth';

export class AuthService {
  static async login(credentials: LoginCredentials): Promise<AuthResponse> {
    try {
      const response = await api.post('/user/login', credentials);
      return response.data;
    } catch (error: any) {
      throw new Error(error.response?.data || 'Erreur de connexion');
    }
  }

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

  static async getCurrentUser(): Promise<User> {
    try {
      const response = await api.get('/user/me');
      return response.data;
    } catch (error: any) {
      throw new Error(error.response?.data || 'Erreur de récupération du profil');
    }
  }

  static async refreshToken(): Promise<AuthResponse> {
    try {
      const response = await api.post('/user/refresh');
      return response.data;
    } catch (error: any) {
      throw new Error('Erreur de rafraîchissement du token');
    }
  }
}