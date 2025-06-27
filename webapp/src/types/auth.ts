/**
 * @file auth.ts
 * @folder types
 * @author PierreDevC
 * @description Authentication type definitions
 */

export interface User {
    id: number;
    username: string;
    email: string;
  }
  
  export interface LoginCredentials {
    email: string;
    password: string;
  }
  
  export interface RegisterCredentials {
    username: string;
    email: string;
    password: string;
    confirmPassword: string;
  }
  
  export interface AuthResponse {
    token: string;
    user: User;
  }
  
  export interface AuthContextType {
    user: User | null;
    token: string | null;
    isLoading: boolean;
    isAuthenticated: boolean;
    login: (credentials: LoginCredentials) => Promise<void>;
    register: (credentials: RegisterCredentials) => Promise<void>;
    logout: () => void;
    refreshUser: () => Promise<void>;
  }