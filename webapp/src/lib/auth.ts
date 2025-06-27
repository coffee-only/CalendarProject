/**
 * @file auth.ts
 * @folder lib
 * @author PierreDevC
 * @description Authentication library
 */

import Cookies from 'js-cookie';
import { User } from '@/types/auth';

const TOKEN_KEY = 'auth-token';
const USER_KEY = 'auth-user';

export const setToken = (token: string): void => {
  Cookies.set(TOKEN_KEY, token, { 
    expires: 7, // 7 jours
    secure: process.env.NODE_ENV === 'production',
    sameSite: 'strict'
  });
};

export const getToken = (): string | null => {
  return Cookies.get(TOKEN_KEY) || null;
};

export const removeToken = (): void => {
  Cookies.remove(TOKEN_KEY);
  Cookies.remove(USER_KEY);
};

export const setUser = (user: User): void => {
  Cookies.set(USER_KEY, JSON.stringify(user), {
    expires: 7,
    secure: process.env.NODE_ENV === 'production',
    sameSite: 'strict'
  });
};

export const getUser = (): User | null => {
  const userStr = Cookies.get(USER_KEY);
  return userStr ? JSON.parse(userStr) : null;
};

export const isAuthenticated = (): boolean => {
  return !!getToken();
};