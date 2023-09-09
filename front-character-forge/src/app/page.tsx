import Image from 'next/image'
import styles from './page.module.css'
import Link from 'next/link'
import CustomisableCharacterSheet from './components/CustomisableCharacterSheet'

export default function Home() {
  return (
    <main className={styles.main}>
      <h1>Character Forge! :D</h1>
      <CustomisableCharacterSheet />
    </main>
  )
}
