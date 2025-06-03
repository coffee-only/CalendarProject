"use client";

import { Button } from "@/components/ui/button";
import { Calendar, Users, Shield, Zap } from "lucide-react";
import { SignedIn, SignedOut, SignInButton, UserButton, useUser } from "@clerk/nextjs";
import Link from "next/link";

export default function Home() {
  const { user } = useUser();

  return (
    <div className="min-h-screen bg-gradient-to-br from-blue-50 to-indigo-100 dark:from-gray-900 dark:to-gray-800">
      {/* Navigation */}
      <nav className="container mx-auto px-6 py-4">
        <div className="flex items-center justify-between">
          <div className="flex items-center space-x-2">
            <Calendar className="h-8 w-8 text-blue-600" />
            <h1 className="text-2xl font-bold text-gray-900 dark:text-white">
              CalendApp
            </h1>
          </div>
          
          <div className="flex items-center space-x-4">
            <SignedOut>
              <SignInButton mode="modal">
                <Button variant="ghost">Se connecter</Button>
              </SignInButton>
              <Button>Commencer</Button>
            </SignedOut>
            
            <SignedIn>
              <span className="text-sm text-gray-600 dark:text-gray-300">
                Salut, {user?.firstName || "Utilisateur"} ! 👋
              </span>
              <UserButton afterSignOutUrl="/" />
            </SignedIn>
          </div>
        </div>
      </nav>

      {/* Hero Section */}
      <main className="container mx-auto px-6 py-16">
        <div className="text-center max-w-4xl mx-auto">
          <h2 className="text-5xl font-bold text-gray-900 dark:text-white mb-6">
            Organisez votre temps avec{" "}
            <span className="text-blue-600">simplicité</span>
          </h2>
          
          <p className="text-xl text-gray-600 dark:text-gray-300 mb-8 leading-relaxed">
            Un calendrier collaboratif moderne et intuitif pour gérer vos événements, 
            planifier vos réunions et synchroniser votre équipe.
          </p>

          <SignedOut>
            <div className="flex justify-center space-x-4 mb-16">
              <SignInButton mode="modal">
                <Button size="lg" className="text-lg px-8 py-3">
                  Essayer gratuitement
                </Button>
              </SignInButton>
              <Button variant="outline" size="lg" className="text-lg px-8 py-3">
                En savoir plus
              </Button>
            </div>
          </SignedOut>

          <SignedIn>
            <div className="bg-green-50 dark:bg-green-900/20 border border-green-200 dark:border-green-800 rounded-lg p-6 mb-16">
              <h3 className="text-lg font-semibold text-green-800 dark:text-green-200 mb-2">
                🎉 Vous êtes connecté(e) !
              </h3>
              <p className="text-green-700 dark:text-green-300 mb-4">
                Bienvenue dans CalendApp. Vous pouvez maintenant accéder à toutes les fonctionnalités.
              </p>
              <Link href="/dashboard">
                <Button size="lg">Accéder au Dashboard</Button>
              </Link>
            </div>
          </SignedIn>

          {/* Features */}
          <div className="grid md:grid-cols-3 gap-8 mt-16">
            <div className="text-center p-6 bg-white dark:bg-gray-800 rounded-lg shadow-sm">
              <Users className="h-12 w-12 text-blue-600 mx-auto mb-4" />
              <h3 className="text-xl font-semibold text-gray-900 dark:text-white mb-2">
                Collaboration
              </h3>
              <p className="text-gray-600 dark:text-gray-300">
                Partagez vos calendriers et travaillez en équipe
              </p>
            </div>

            <div className="text-center p-6 bg-white dark:bg-gray-800 rounded-lg shadow-sm">
              <Shield className="h-12 w-12 text-blue-600 mx-auto mb-4" />
              <h3 className="text-xl font-semibold text-gray-900 dark:text-white mb-2">
                Sécurisé
              </h3>
              <p className="text-gray-600 dark:text-gray-300">
                Vos données sont protégées et chiffrées
              </p>
            </div>

            <div className="text-center p-6 bg-white dark:bg-gray-800 rounded-lg shadow-sm">
              <Zap className="h-12 w-12 text-blue-600 mx-auto mb-4" />
              <h3 className="text-xl font-semibold text-gray-900 dark:text-white mb-2">
                Rapide
              </h3>
              <p className="text-gray-600 dark:text-gray-300">
                Interface moderne et réactive
              </p>
            </div>
          </div>
        </div>
      </main>

      {/* Test Auth Section */}
      <section className="bg-gray-50 dark:bg-gray-800/50 py-16">
        <div className="container mx-auto px-6 text-center">
          <h3 className="text-2xl font-bold text-gray-900 dark:text-white mb-8">
            Test d'authentification
          </h3>
          
          <SignedOut>
            <div className="bg-white dark:bg-gray-800 p-8 rounded-lg shadow max-w-md mx-auto">
              <p className="text-gray-600 dark:text-gray-300 mb-4">
                Vous n'êtes pas connecté(e)
              </p>
              <SignInButton mode="modal">
                <Button className="w-full">Se connecter pour tester</Button>
              </SignInButton>
            </div>
          </SignedOut>

          <SignedIn>
            <div className="bg-white dark:bg-gray-800 p-8 rounded-lg shadow max-w-md mx-auto">
              <div className="flex items-center justify-center mb-4">
                <UserButton />
                <div className="ml-4 text-left">
                  <p className="font-semibold text-gray-900 dark:text-white">
                    {user?.firstName} {user?.lastName}
                  </p>
                  <p className="text-sm text-gray-600 dark:text-gray-300">
                    {user?.primaryEmailAddress?.emailAddress}
                  </p>
                </div>
              </div>
              <p className="text-green-600 font-medium">
                ✅ Authentification réussie !
              </p>
            </div>
          </SignedIn>
        </div>
      </section>
    </div>
  );
}
