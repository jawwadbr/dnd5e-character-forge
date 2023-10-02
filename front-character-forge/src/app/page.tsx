import Image from 'next/image'
import styles from './page.module.css'
import Link from 'next/link'
import CharacterGenerator from './components/CharacterGenerator'

export default function Home() {
  return (
    <main className={styles.main}>
      <h1>Character Forge! :D</h1>
      <CharacterGenerator />
    </main>
  )
}
