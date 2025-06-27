/**
 * @file page.tsx
 * @folder app/dashboard
 * @author PierreDevC
 * @description Dashboard page
 */

import {
  Breadcrumb,
  BreadcrumbItem,
  BreadcrumbLink,
  BreadcrumbList,
  BreadcrumbPage,
  BreadcrumbSeparator,
} from "@/components/ui/breadcrumb"
import { Separator } from "@/components/ui/separator"
import {
  SidebarTrigger,
} from "@/components/ui/sidebar"
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "@/components/ui/card"
import { Button } from "@/components/ui/button"

export default function DashboardPage() {
  return (
    <>
      <header className="flex h-16 shrink-0 items-center gap-2 border-b px-4">
        <SidebarTrigger className="-ml-1" />
        <Separator orientation="vertical" className="mr-2 h-4" />
        <Breadcrumb>
          <BreadcrumbList>
            <BreadcrumbItem className="hidden md:block">
              <BreadcrumbLink href="/dashboard">
                Calendrier Collaboratif
              </BreadcrumbLink>
            </BreadcrumbItem>
            <BreadcrumbSeparator className="hidden md:block" />
            <BreadcrumbItem>
              <BreadcrumbPage>Dashboard</BreadcrumbPage>
            </BreadcrumbItem>
          </BreadcrumbList>
        </Breadcrumb>
      </header>
      
      <div className="flex flex-1 flex-col gap-4 p-4">
        <div className="grid auto-rows-min gap-4 md:grid-cols-3">
          <Card>
            <CardHeader>
              <CardTitle>Événements aujourd'hui</CardTitle>
              <CardDescription>
                Vos événements pour la journée
              </CardDescription>
            </CardHeader>
            <CardContent>
              <div className="text-2xl font-bold">3</div>
              <p className="text-xs text-muted-foreground">
                +2 par rapport à hier
              </p>
            </CardContent>
          </Card>
          
          <Card>
            <CardHeader>
              <CardTitle>Équipes actives</CardTitle>
              <CardDescription>
                Équipes que vous gérez
              </CardDescription>
            </CardHeader>
            <CardContent>
              <div className="text-2xl font-bold">12</div>
              <p className="text-xs text-muted-foreground">
                3 nouvelles cette semaine
              </p>
            </CardContent>
          </Card>
          
          <Card>
            <CardHeader>
              <CardTitle>Invitations</CardTitle>
              <CardDescription>
                En attente de réponse
              </CardDescription>
            </CardHeader>
            <CardContent>
              <div className="text-2xl font-bold">5</div>
              <p className="text-xs text-muted-foreground">
                2 urgentes
              </p>
            </CardContent>
          </Card>
        </div>
        
        <div className="min-h-[100vh] flex-1 rounded-xl bg-muted/50 md:min-h-min">
          <Card className="h-full">
            <CardHeader>
              <CardTitle>Activité récente</CardTitle>
              <CardDescription>
                Vos dernières actions sur la plateforme
              </CardDescription>
            </CardHeader>
            <CardContent>
              <div className="space-y-4">
                <div className="flex items-center gap-4">
                  <div className="h-2 w-2 rounded-full bg-blue-500" />
                  <div className="flex-1">
                    <p className="text-sm font-medium">Nouvel événement créé</p>
                    <p className="text-xs text-muted-foreground">Il y a 2 heures</p>
                  </div>
                </div>
                <div className="flex items-center gap-4">
                  <div className="h-2 w-2 rounded-full bg-green-500" />
                  <div className="flex-1">
                    <p className="text-sm font-medium">Invitation acceptée</p>
                    <p className="text-xs text-muted-foreground">Il y a 4 heures</p>
                  </div>
                </div>
                <div className="flex items-center gap-4">
                  <div className="h-2 w-2 rounded-full bg-orange-500" />
                  <div className="flex-1">
                    <p className="text-sm font-medium">Équipe rejointe</p>
                    <p className="text-xs text-muted-foreground">Hier</p>
                  </div>
                </div>
              </div>
              
              <div className="mt-6 flex gap-2">
                <Button>Créer un événement</Button>
                <Button variant="outline">Voir le calendrier</Button>
              </div>
            </CardContent>
          </Card>
        </div>
      </div>
    </>
  )
}