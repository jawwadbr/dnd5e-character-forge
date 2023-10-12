import './globals.css'
import type { Metadata } from 'next'
import { Overpass } from 'next/font/google'
import styles from './page.module.css'

const overpass = Overpass({
  weight: ['100', '300', '500', '700', '800'],
  style: ['normal'],
  subsets: ['latin'],
  display: 'swap'
})

export const metadata: Metadata = {
  title: 'Character Forge',
  description: 'Build your DnD Character quickly',
}

export default function RootLayout({
  children,
}: {
  children: React.ReactNode
}) {
  return (
    <html lang="en">
      <body className={overpass.className}>
        <main className={styles.main}>
          {children}
        </main>
      </body>
    </html>
  )
}
