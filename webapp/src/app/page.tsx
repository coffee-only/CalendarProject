/**
 * @file page.tsx
 * @folder app
 * @author PierreDevC
 * @description Home page
 */

import { Button } from "@/components/ui/button"
import { Navbar } from "@/components/navbar"
import Link from "next/link"

export default function Home() {
  return (
    <div className="min-h-screen bg-background">
      <Navbar />
      
      {/* Hero Section */}
      <section className="mx-auto max-w-7xl px-4 sm:px-6 lg:px-8 flex min-h-[calc(100vh-3.5rem)] flex-col items-center justify-center space-y-8 py-12">
        <div className="flex max-w-[980px] flex-col items-center gap-2 text-center">
          <h1 className="text-3xl font-extrabold leading-tight tracking-tighter md:text-4xl">
            Calendrier Collaboratif
          </h1>
          <p className="max-w-[700px] text-lg text-muted-foreground">
            Organisez vos événements et collaborez efficacement avec votre équipe. 
            Simple, rapide et intuitif.
          </p>
        </div>
        
        <div className="flex gap-4">
          <Button size="lg" asChild>
            <Link href="/login">
              Commencer
            </Link>
          </Button>
          <Button variant="outline" size="lg" asChild>
            <Link href="/dashboard">
              Tester dashboard (no auth)
            </Link>
          </Button>
        </div>
      </section>

      {/* Features Section */}
      <section className="mx-auto max-w-7xl px-4 sm:px-6 lg:px-8 py-24">
        <div className="grid grid-cols-1 gap-8 md:grid-cols-3">
          <div className="flex flex-col items-center space-y-2 text-center">
            <div className="rounded-lg bg-muted p-3 text-2xl">
              📅
            </div>
            <h3 className="text-xl font-bold">Planning Simple</h3>
            <p className="text-muted-foreground">
              Interface intuitive pour créer et gérer vos événements
            </p>
          </div>
          
          <div className="flex flex-col items-center space-y-2 text-center">
            <div className="rounded-lg bg-muted p-3 text-2xl">
              👥
            </div>
            <h3 className="text-xl font-bold">Collaboration</h3>
            <p className="text-muted-foreground">
              Travaillez en équipe et partagez vos calendriers
            </p>
          </div>
          
          <div className="flex flex-col items-center space-y-2 text-center">
            <div className="rounded-lg bg-muted p-3 text-2xl">
              🔔
            </div>
            <h3 className="text-xl font-bold">Notifications</h3>
            <p className="text-muted-foreground">
              Recevez des rappels pour ne rien manquer
            </p>
          </div>
        </div>
      </section>
    </div>
  )
}