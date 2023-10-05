'use client'
import { useRouter } from "next/navigation"
import styles from './CharacterGenerator.module.css'

export default function CharacterGenerator() {
    const races: string[] = ["Human", "Elf", "Half elf", "Drow", "Dwarf", "Orc", "Gnome", "Tiefling", "Aasimar"]
    const classes: string[] = ["Barbarian", "Warrior", "Rogue", "Monk", "Paladin", "Sorcerer", "Bard", "Warlock", "Wizard", "Druid"]
    const backgrounds: string[] = ["Acolyte", "Charlatan", "Entertainer", "Folk Hero", "Hermit", "Knight", "Noble", "Outlander", "Sage"]
    const levels: number[] = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20]
    const router = useRouter()

    const generateCharacter = () => {
        router.push('/generated-character', { scroll: false })
    }

    return (
        <div className={styles.charGeneratorContainer}>
            <form>
                <div className={styles.charField}>
                    <label htmlFor="name">Name</label>
                    <input type="text" name="name" id="name" placeholder="Kairus Stronghold" />
                </div>
                <div className={styles.charField}>
                    <label htmlFor="races">Race</label>
                    <select id="races" name="races">
                        {races.map((race, index) =>
                            <option key={index} value={race}>{race}</option>
                        )}
                    </select>
                </div>
                <div className={styles.charField}>
                    <label htmlFor="classes">Class</label>
                    <select id="classes" name="classes">
                        {classes.map((classDnd, index) =>
                            <option key={index} value={classDnd}>{classDnd}</option>
                        )}
                    </select>

                </div>
                <div className={styles.charField}>
                    <label htmlFor="backgrounds">Background</label>
                    <select id="backgrounds" name="backgrounds">
                        {backgrounds.map((background, index) =>
                            <option key={index} value={background}>{background}</option>
                        )}
                    </select>
                </div>
                <div className={styles.charField}>
                    <label htmlFor="levels">Level</label>
                    <select id="levels" name="levels">
                        {levels.map((level, index) =>
                            <option key={index} value={level}>{level}</option>
                        )}
                    </select>
                </div>
                <div className={styles.buttonGenerate}>
                    <button type="button" onClick={() => generateCharacter()}>Generate</button>
                </div>
            </form>
        </div>
    )
}