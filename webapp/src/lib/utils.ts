/**
 * @file utils.ts
 * @folder lib
 * @author PierreDevC
 * @description Utility functions
 */

import { clsx, type ClassValue } from "clsx"
import { twMerge } from "tailwind-merge"

export function cn(...inputs: ClassValue[]) {
  return twMerge(clsx(inputs))
}
