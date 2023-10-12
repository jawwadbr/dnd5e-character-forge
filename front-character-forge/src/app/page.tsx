import Image from 'next/image'
import Link from 'next/link'
import CharacterGenerator from './components/CharacterGenerator'

export default function Home() {
  return (
    <>
      <h1>Character Forge! :D</h1>
      <CharacterGenerator />
    </>
  )
}
