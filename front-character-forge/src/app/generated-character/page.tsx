import Link from "next/link";
import CustomisableCharacterSheet from "../components/CustomisableCharacterSheet";
import styles from "./GeneratedCharacter.module.css"

export default function Page() {
    return (
        <>
            <Link href='/' className={styles.homelink}>Return to Home</Link>
            <CustomisableCharacterSheet />
        </>

    )
}