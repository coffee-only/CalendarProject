/**
 * @file layout.tsx
 * @folder app/dashboard
 * @author PierreDevC
 * @description Dashboard layout
 */

import { SidebarProvider, SidebarInset } from "@/components/ui/sidebar"
import { AppSidebar } from "@/components/app-sidebar"
import ProtectedRoute from "@/components/ProtectedRoute"

export default function DashboardLayout({
  children,
}: {
  children: React.ReactNode
}) {
  return (
    <ProtectedRoute>
      <SidebarProvider>
        <AppSidebar />
        <SidebarInset>
          {children}
        </SidebarInset>
      </SidebarProvider>
    </ProtectedRoute>
  )
}