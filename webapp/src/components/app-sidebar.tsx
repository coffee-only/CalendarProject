"use client"

import * as React from "react"
import {
  Calendar,
  CalendarPlus,
  Users,
  Settings,
  Bell,
  BarChart3,
  Clock,
  Share2,
  Home,
} from "lucide-react"

import { NavMain } from "@/components/nav-main"
import { NavProjects } from "@/components/nav-projects"
import { NavSecondary } from "@/components/nav-secondary"
import { NavUser } from "@/components/nav-user"
import {
  Sidebar,
  SidebarContent,
  SidebarFooter,
  SidebarHeader,
  SidebarMenu,
  SidebarMenuButton,
  SidebarMenuItem,
} from "@/components/ui/sidebar"
import { useAuth } from "@/contexts/AuthContext"

export function AppSidebar({ ...props }: React.ComponentProps<typeof Sidebar>) {
  const { user } = useAuth()

  const data = {
    navMain: [
      {
        title: "Dashboard",
        url: "/dashboard",
        icon: Home,
        isActive: true,
        items: [
          {
            title: "Vue d'ensemble",
            url: "/dashboard",
          },
          {
            title: "Événements récents",
            url: "/dashboard/recent",
          },
          {
            title: "Statistiques",
            url: "/dashboard/stats",
          },
        ],
      },
      {
        title: "Calendriers",
        url: "/calendars",
        icon: Calendar,
        items: [
          {
            title: "Mes calendriers",
            url: "/calendars/mine",
          },
          {
            title: "Calendriers partagés",
            url: "/calendars/shared",
          },
          {
            title: "Calendrier public",
            url: "/calendars/public",
          },
          {
            title: "Archives",
            url: "/calendars/archived",
          },
        ],
      },
      {
        title: "Événements",
        url: "/events",
        icon: CalendarPlus,
        items: [
          {
            title: "Créer un événement",
            url: "/events/create",
          },
          {
            title: "Mes événements",
            url: "/events/mine",
          },
          {
            title: "Invitations reçues",
            url: "/events/invitations",
          },
          {
            title: "Événements récurrents",
            url: "/events/recurring",
          },
        ],
      },
      {
        title: "Équipes",
        url: "/teams",
        icon: Users,
        items: [
          {
            title: "Mes équipes",
            url: "/teams/mine",
          },
          {
            title: "Créer une équipe",
            url: "/teams/create",
          },
          {
            title: "Inviter des membres",
            url: "/teams/invite",
          },
          {
            title: "Gestion des rôles",
            url: "/teams/roles",
          },
        ],
      },
      {
        title: "Rapports",
        url: "/reports",
        icon: BarChart3,
        items: [
          {
            title: "Utilisation",
            url: "/reports/usage",
          },
          {
            title: "Participation",
            url: "/reports/participation",
          },
          {
            title: "Tendances",
            url: "/reports/trends",
          },
        ],
      },
    ],
    navSecondary: [
      {
        title: "Notifications",
        url: "/notifications",
        icon: Bell,
      },
      {
        title: "Historique",
        url: "/history",
        icon: Clock,
      },
      {
        title: "Partage",
        url: "/sharing",
        icon: Share2,
      },
      {
        title: "Paramètres",
        url: "/settings",
        icon: Settings,
      },
    ],
    projects: [
      {
        name: "Équipe Marketing",
        url: "/teams/marketing",
        icon: Users,
      },
      {
        name: "Projet Alpha",
        url: "/teams/alpha",
        icon: Users,
      },
      {
        name: "Formation RH",
        url: "/teams/hr",
        icon: Users,
      },
    ],
  }

  return (
    <Sidebar variant="inset" {...props}>
      <SidebarHeader>
        <SidebarMenu>
          <SidebarMenuItem>
            <SidebarMenuButton size="lg" asChild>
              <a href="/dashboard">
                <div className="bg-sidebar-primary text-sidebar-primary-foreground flex aspect-square size-8 items-center justify-center rounded-lg">
                  <Calendar className="size-4" />
                </div>
                <div className="grid flex-1 text-left text-sm leading-tight">
                  <span className="truncate font-medium">Calendrier Collaboratif</span>
                  <span className="truncate text-xs">
                    {user ? `Connecté comme ${user.username}` : 'Application'}
                  </span>
                </div>
              </a>
            </SidebarMenuButton>
          </SidebarMenuItem>
        </SidebarMenu>
      </SidebarHeader>
      <SidebarContent>
        <NavMain items={data.navMain} />
        <NavProjects projects={data.projects} />
        <NavSecondary items={data.navSecondary} className="mt-auto" />
      </SidebarContent>
      <SidebarFooter>
        <NavUser />
      </SidebarFooter>
    </Sidebar>
  )
}