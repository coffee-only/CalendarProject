/**
 * @file layout.tsx
 * @project Java Calendar (Frontend)
 * @developer PierreDevC
 * @created 2024-06-01
 * @description Main layout component that wraps all pages
 * @version 1.0.0
 * @license MIT
 */

import type { Metadata } from "next";
import { Geist, Geist_Mono } from "next/font/google";
import "./globals.css";
// import { SchedulerProvider } from "@/providers/schedular-provider"; // TODO: Add this back in when we have a schedular provider
import { ClerkProvider } from "@clerk/nextjs";

const geistSans = Geist({
  variable: "--font-geist-sans",
  subsets: ["latin"],
});

const geistMono = Geist_Mono({
  variable: "--font-geist-mono",
  subsets: ["latin"],
});

export const metadata: Metadata = {
  title: "Mina Scheduler - NextJS/React Shadcn UI Calendar",
  description:
    "A lightweight, customizable calendar and scheduler component built with NextJS/React and Shadcn UI | Next UI Calendar",
  keywords:
    "shadcn ui, shadcn, shadcn full calendar, calendar, scheduler, tailwind, react, nextjs, event management, date picker, next ui calendar, mina scheduler, full calendar, Mina Scheduler",
  authors: [{ name: "Mina Massoud" }],
  viewport: "width=device-width, initial-scale=1",
  openGraph: {
    type: "website",
    locale: "en_US",
    url: "https://mina-scheduler.vercel.app/",
    title: "Mina Scheduler - NextJS/React Shadcn UI Calendar",
    description: "A lightweight, customizable calendar and scheduler component built with NextJS/React and Shadcn UI",
    siteName: "Mina Scheduler",
    images: [
      {
        url: "https://mina-scheduler.vercel.app/opengraph.png",
        width: 1200,
        height: 630,
        alt: "Mina Scheduler - A customizable scheduler for NextJS and React"
      }
    ]
  },
  twitter: {
    card: "summary_large_image",
    title: "Mina Scheduler - NextJS/React Shadcn UI Calendar",
    description: "A lightweight, customizable calendar and scheduler component built with NextJS/React and Shadcn UI",
    images: ["https://mina-scheduler.vercel.app/opengraph.png"]
  },
};

/**
 * @function RootLayout
 * @description ClerkProvider wrapper for the app
 * @param {Readonly<{ children: React.ReactNode }>} props - The component props
 * @returns {React.ReactNode} The rendered component
 */
export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <ClerkProvider>
      <html lang="en">
        <body
          className={`${geistSans.variable} ${geistMono.variable} antialiased font-[family-name:var(--font-geist-sans)]`}
        >
          {/* <SchedulerProvider weekStartsOn="monday">{children}</SchedulerProvider> // TODO: Add this back in when we have a schedular provider */} 
          {children}
        </body>
      </html>
    </ClerkProvider>
  );
}
