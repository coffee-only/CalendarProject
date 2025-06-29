/**
 * @file auth.ts
 * @folder types
 * @author PierreDevC
 * @description Authentication type definitions
 */

/**
 * @interface User
 * @description User interface
 * @property {number} id - The user's ID
 * @property {string} username - The user's username
 * @property {string} email - The user's email
 */
export interface User {
  id: number;
  username: string;
  email: string;
}

/**
 * @interface LoginCredentials
 * @description Login credentials interface
 * @property {string} email - The user's email
 * @property {string} password - The user's password
 */
export interface LoginCredentials {
  email: string;
  password: string;
}

/**
 * @interface RegisterCredentials
 * @description Register credentials interface
 * @property {string} username - The user's username
 * @property {string} email - The user's email
 * @property {string} password - The user's password
 * @property {string} confirmPassword - The user's confirm password
 */
export interface RegisterCredentials {
  username: string;
  email: string;
  password: string;
  confirmPassword: string;
}

/**
 * @interface AuthResponse
 * @description Auth response interface
 * @property {string} token - The user's token
 * @property {User} user - The user's data
 */
export interface AuthResponse {
  token: string;
  user: User;
}

/**
 * @interface AuthContextType
 * @description Auth context type
 * @property {User | null} user - The user's data
 * @property {string | null} token - The user's token
 * @property {boolean} isLoading - The user's loading state
 * @property {boolean} isAuthenticated - The user's authentication state
 * @property {function} login - The login function
 * @property {function} register - The register function
 * @property {function} logout - The logout function
 * @property {function} refreshUser - The refresh user function
 */
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