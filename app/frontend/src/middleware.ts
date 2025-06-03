/**
 * @file middleware.ts
 * @project Java Calendar (Frontend)
 * @developer PierreDevC
 * @created 2024-06-01
 * @description Clerk middleware for the app
 */

import { clerkMiddleware } from '@clerk/nextjs/server';

/**
 * @function middleware
 * @description Clerk middleware for the app
 * @returns {void}
 */
export default clerkMiddleware();

/**
 * @function config
 * @description Clerk config for the app
 * @returns {void}
 */
export const config = {
  matcher: ["/((?!.+\\.[\\w]+$|_next).*)", "/", "/(api|trpc)(.*)"],
};