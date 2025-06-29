/**
 * @file auth.ts
 * @folder lib
 * @author PierreDevC
 * @description Authentication library
 */

import Cookies from 'js-cookie';
import { User } from '@/types/auth';

/**
 * @description Clés des cookies
 * @type {string}
 */
const TOKEN_KEY = 'auth-token';
const USER_KEY = 'auth-user';

/**
 * @description Définition du token
 * @param {string} token - Le token à définir
 * @param {number} expires - Le nombre de jours pour lequel le token est valide
 * @param {boolean} secure - Environnement de production
 * @param {string} sameSite - Indique si le cookie doit être sécurisé
 * @returns {void}
 */
export const setToken = (token: string): void => {
  Cookies.set(TOKEN_KEY, token, { 
    expires: 7, // 7 jours
    secure: process.env.NODE_ENV === 'production',
    sameSite: 'strict'
  });
};

/**
 * @description Récupération du token
 * @returns {string | null} Le token
 */
export const getToken = (): string | null => {
  return Cookies.get(TOKEN_KEY) || null;
};

/**
 * @description Suppression du token
 * @returns {void}
 */
export const removeToken = (): void => {
  Cookies.remove(TOKEN_KEY);
  Cookies.remove(USER_KEY);
};

/**
 * @description Définition de l'utilisateur
 * @param {User} user - L'utilisateur à définir
 * @returns {void}
 */
export const setUser = (user: User): void => {
  Cookies.set(USER_KEY, JSON.stringify(user), {
    expires: 7,
    secure: process.env.NODE_ENV === 'production',
    sameSite: 'strict'
  });
};

/**
 * @description Récupération de l'utilisateur
 * @returns {User | null} L'utilisateur
 */
export const getUser = (): User | null => {
  const userStr = Cookies.get(USER_KEY);
  return userStr ? JSON.parse(userStr) : null;
};

/**
 * @description Vérification de l'authentification
 * @returns {boolean} Indique si l'utilisateur est authentifié
 */
export const isAuthenticated = (): boolean => {
  return !!getToken();
};