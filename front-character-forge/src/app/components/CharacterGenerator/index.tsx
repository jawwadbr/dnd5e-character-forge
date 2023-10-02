import Link from "next/link"
import styles from './CharacterGenerator.module.css'

export default function CharacterGenerator() {


    return (
        <div className={styles.charGeneratorContainer}>
            <form>
                <div className={styles.charField}>
                    <input type="text" placeholder="Name" />
                </div>

                <div className={styles.charField}>
                    <input type="text" placeholder="Race" />
                </div>

                <div className={styles.charField}>
                    <input type="text" placeholder="Class" />
                </div>

                <div className={styles.charField}>
                    <input type="text" placeholder="Background" />
                </div>
                <div className={styles.charField}>
                    <input type="text" placeholder="Level" />
                </div>
                <div className={styles.buttonGenerate}>
                    <div>
                        <Link href='/generated-character'>Generate</Link>
                    </div>
                </div>

            </form>
        </div>
    )
}