/**
 * @file middleware.ts
 * @folder middleware
 * @author PierreDevC
 * @description Middleware for authentication
 */

import { NextResponse } from 'next/server';
import type { NextRequest } from 'next/server';

// Routes qui nécessitent une authentification
const protectedRoutes = ['/dashboard'];

// Routes qui ne doivent pas être accessibles aux utilisateurs connectés
const authRoutes = ['/login', '/register'];

export function middleware(request: NextRequest) {
  const token = request.cookies.get('auth-token')?.value;
  const { pathname } = request.nextUrl;

  // Mode développement - bypass l'authentification
  const isDev = true; 
  
  if (isDev) {
    return NextResponse.next();
  }

  // Rediriger les utilisateurs connectés depuis les pages auth
  if (authRoutes.includes(pathname) && token) {
    return NextResponse.redirect(new URL('/dashboard', request.url));
  }

  // Rediriger les utilisateurs non connectés depuis les pages protégées
  if (protectedRoutes.some(route => pathname.startsWith(route)) && !token) {
    return NextResponse.redirect(new URL('/login', request.url));
  }

  return NextResponse.next();
}

export const config = {
  matcher: [
    /*
     * Matcher pour toutes les routes sauf :
     * - api (API routes)
     * - _next/static (static files)
     * - _next/image (image optimization files)
     * - favicon.ico (favicon file)
     */
    '/((?!api|_next/static|_next/image|favicon.ico).*)',
  ],
};