/**
 * @file api.ts
 * @folder lib
 * @author PierreDevC
 * @description API configuration
 */

import axios from 'axios';
import { getToken, removeToken } from '@/lib/auth';

/**
 * @description URL de base de l'API
 * @type {string}
 */
const API_BASE_URL = process.env.NEXT_PUBLIC_API_URL || 'http://localhost:8080';

/**
 * @description Création de l'instance axios
 * @type {AxiosInstance}
 */
export const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

/**
 * @description Intercepteur pour ajouter le token à chaque requête
 * @param {AxiosRequestConfig} config - La configuration de la requête
 * @returns {AxiosRequestConfig} La configuration de la requête
 */
api.interceptors.request.use(
  (config) => {
    const token = getToken();
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

// Intercepteur pour gérer les réponses et les erreurs d'authentification
/**
 * @description Intercepteur pour gérer les réponses et les erreurs d'authentification
 * @param {AxiosResponse} response - La réponse de l'API
 * @returns {AxiosResponse} La réponse de l'API
 */
api.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response?.status === 401) {
      removeToken();
      window.location.href = '/login';
    }
    return Promise.reject(error);
  }
);

export default api;