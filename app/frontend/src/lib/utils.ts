/**
 * @file utils.ts
 * @project Java Calendar (Frontend)
 * @developer PierreDevC
 * @created 2024-06-01
 * @description Utility functions
 */

import { clsx, type ClassValue } from "clsx"
import { twMerge } from "tailwind-merge"

export function cn(...inputs: ClassValue[]) {
  return twMerge(clsx(inputs))
}