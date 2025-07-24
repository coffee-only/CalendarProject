/**
 * @file ProtectedRoute.tsx
 * @folder components
 * @author PierreDevC
 * @description Protected route component
 */

'use client';

import { useAuth } from '@/contexts/AuthContext'; 
import { ReactNode } from 'react';

/**
 * @interface ProtectedRouteProps
 * @description Props for the protected route component
 * @property {ReactNode} children - The children of the protected route
 * @property {ReactNode} fallback - The fallback component to show while loading
 */
interface ProtectedRouteProps {
  children: ReactNode;
  fallback?: ReactNode;
}

/**
 * @function ProtectedRoute
 * @description Protected route component
 * @param {ProtectedRouteProps} props - The props for the protected route component
 * @returns {ReactNode} The protected route component
 */
export default function ProtectedRoute({ children, fallback }: ProtectedRouteProps) {
  const { isAuthenticated, isLoading } = useAuth();

  if (isLoading) {
    return (
      <div className="flex items-center justify-center min-h-screen">
        <div className="text-center">
          <div className="animate-spin rounded-full h-32 w-32 border-b-2 border-gray-900"></div>
          <p className="mt-4">Chargement...</p>
        </div>
      </div>
    );
  }

  if (!isAuthenticated) {
    return fallback || null;
  }

  return <>{children}</>;
}