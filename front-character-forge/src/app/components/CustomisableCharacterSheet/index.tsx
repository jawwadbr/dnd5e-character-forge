import styles from './CustomisableCharacterSheet.module.css'

export default function CustomisableCharacterSheet() {
    async function onSubmit() {

    }

    return (
        <form className={styles.charsheet}>
            <div className={styles.headerCharsheet}>
                <section className={styles.charname}>
                    <label htmlFor="charname">Character Name</label><input name="charname" placeholder="Kairus Stronghold" />
                </section>
                <section className={styles.misc}>
                    <ul>
                        <li>
                            <label htmlFor="classlevel">Class & Level</label>
                            <input name="classlevel" placeholder="Paladin 1" />
                        </li>
                        <li>
                            <label htmlFor="background">Background</label>
                            <input name="background" placeholder="Soldier" />
                        </li>
                        <li>
                            <label htmlFor="playername">Player Name</label>
                            <input name="playername" placeholder="Your name here" />
                        </li>
                        <li>
                            <label htmlFor="race">Race</label>
                            <input name="race" placeholder="Half-elf" />
                        </li>
                        <li>
                            <label htmlFor="alignment">Alignment</label>
                            <input name="alignment" placeholder="Lawful Good" />
                        </li>
                        <li>
                            <label htmlFor="experiencepoints">Experience Points</label>
                            <input name="experiencepoints" placeholder="2450" />
                        </li>
                    </ul>
                </section>
            </div>
            <div className={styles.mainContent}>
                <section>
                    <section className={styles.attributes}>
                        <div className={styles.scores}>
                            <ul>
                                <li>
                                    <div className={styles.score}>
                                        <label htmlFor="Strengthscore">Strength</label>
                                        <input name="Strengthscore" placeholder="10" className={styles.stat} />
                                    </div>
                                    <div className={styles.modifier}>
                                        <input name="Strengthmod" placeholder="+0" className={styles.statmod} />
                                    </div>
                                </li>
                                <li>
                                    <div className={styles.score}>
                                        <label htmlFor="Dexterityscore">Dexterity</label>
                                        <input name="Dexterityscore" placeholder="10" className={styles.stat} />
                                    </div>
                                    <div className={styles.modifier}>
                                        <input name="Dexteritymod" placeholder="+0" className={styles.statmod} />
                                    </div>
                                </li>
                                <li>
                                    <div className={styles.score}>
                                        <label htmlFor="Constitutionscore">Constitution</label>
                                        <input name="Constitutionscore" placeholder="10" className={styles.stat} />
                                    </div>
                                    <div className={styles.modifier}>
                                        <input name="Constitutionmod" placeholder="+0" className={styles.statmod} />
                                    </div>
                                </li>
                                <li>
                                    <div className={styles.score}>
                                        <label htmlFor="Wisdomscore">Wisdom</label>
                                        <input name="Wisdomscore" placeholder="10" className={styles.stat} />
                                    </div>
                                    <div className={styles.modifier}>
                                        <input name="Wisdommod" placeholder="+0" />
                                    </div>
                                </li>
                                <li>
                                    <div className={styles.score}>
                                        <label htmlFor="Intelligencescore">Intelligence</label>
                                        <input name="Intelligencescore" placeholder="10" className={styles.stat} />
                                    </div>
                                    <div className={styles.modifier}>
                                        <input name="Intelligencemod" placeholder="+0" className={styles.statmod} />
                                    </div>
                                </li>
                                <li>
                                    <div className={styles.score}>
                                        <label htmlFor="Charismascore">Charisma</label>
                                        <input name="Charismascore" placeholder="10" className={styles.stat} />
                                    </div>
                                    <div className={styles.modifier}>
                                        <input name="Charismamod" placeholder="+0" className={styles.statmod} />
                                    </div>
                                </li>
                            </ul>
                        </div>
                        <div className={styles.attrApplications}>
                            <div className={`${styles.inspiration} ${styles.box}`}>
                                <div className={styles.labelContainer}>
                                    <label htmlFor="inspiration">Inspiration</label>
                                </div>
                                <input name="inspiration" type="checkbox" />
                            </div>
                            <div className={`${styles.proficiencybonus} ${styles.box}`}>
                                <div className={styles.labelContainer}>
                                    <label htmlFor="proficiencybonus">Proficiency Bonus</label>
                                </div>
                                <input name="proficiencybonus" placeholder="+2" />
                            </div>
                            <div className={`${styles.saves} ${styles.listSection} ${styles.box}`}>
                                <ul>
                                    <li>
                                        <label htmlFor="Strength-save">Strength</label><input name="Strength-save" placeholder="+0" type="text" />
                                        <input name="Strength-save-prof" type="checkbox" />
                                    </li>
                                    <li>
                                        <label htmlFor="Dexterity-save">Dexterity</label><input name="Dexterity-save" placeholder="+0" type="text" />
                                        <input name="Dexterity-save-prof" type="checkbox" />
                                    </li>
                                    <li>
                                        <label htmlFor="Constitution-save">Constitution</label><input name="Constitution-save" placeholder="+0" type="text" />
                                        <input name="Constitution-save-prof" type="checkbox" />
                                    </li>
                                    <li>
                                        <label htmlFor="Wisdom-save">Wisdom</label><input name="Wisdom-save" placeholder="+0" type="text" />
                                        <input name="Wisdom-save-prof" type="checkbox" />
                                    </li>
                                    <li>
                                        <label htmlFor="Intelligence-save">Intelligence</label><input name="Intelligence-save" placeholder="+0" type="text" />
                                        <input name="Intelligence-save-prof" type="checkbox" />
                                    </li>
                                    <li>
                                        <label htmlFor="Charisma-save">Charisma</label><input name="Charisma-save" placeholder="+0" type="text" />
                                        <input name="Charisma-save-prof" type="checkbox" />
                                    </li>
                                </ul>
                                <div className={styles.label}>
                                    Saving Throws
                                </div>
                            </div>
                            <div className={`${styles.skills} ${styles.listSection} ${styles.box}`}>
                                <ul>
                                    <li>
                                        <label htmlFor="Acrobatics">Acrobatics<span className={styles.skill}>(Dex)</span></label>
                                        <input name="Acrobatics" placeholder="+0" type="text" /><input name="Acrobatics-prof" type="checkbox" />
                                    </li>
                                    <li>
                                        <label htmlFor="Animal Handling">Animal Handling<span className={styles.skill}>(Wis)</span></label>
                                        <input name="Animal Handling" placeholder="+0" type="text" /><input name="Animal Handling-prof" type="checkbox" />
                                    </li>
                                    <li>
                                        <label htmlFor="Arcana">Arcana<span className={styles.skill}>(Int)</span></label>
                                        <input name="Arcana" placeholder="+0" type="text" /><input name="Arcana-prof" type="checkbox" />
                                    </li>
                                    <li>
                                        <label htmlFor="Athletics">Athletics<span className={styles.skill}>(Str)</span></label>
                                        <input name="Athletics" placeholder="+0" type="text" /><input name="Athletics-prof" type="checkbox" />
                                    </li>
                                    <li>
                                        <label htmlFor="Deception">Deception<span className={styles.skill}>(Cha)</span></label>
                                        <input name="Deception" placeholder="+0" type="text" /><input name="Deception-prof" type="checkbox" />
                                    </li>
                                    <li>
                                        <label htmlFor="History">History<span className={styles.skill}>(Int)</span></label>
                                        <input name="History" placeholder="+0" type="text" /><input name="History-prof" type="checkbox" />
                                    </li>
                                    <li>
                                        <label htmlFor="Insight">Insight<span className={styles.skill}>(Wis)</span></label>
                                        <input name="Insight" placeholder="+0" type="text" /><input name="Insight-prof" type="checkbox" />
                                    </li>
                                    <li>
                                        <label htmlFor="Intimidation">Intimidation<span className={styles.skill}>(Cha)</span></label>
                                        <input name="Intimidation" placeholder="+0" type="text" /><input name="Intimidation-prof" type="checkbox" />
                                    </li>
                                    <li>
                                        <label htmlFor="Investigation">Investigation<span className={styles.skill}>(Int)</span></label>
                                        <input name="Investigation" placeholder="+0" type="text" /><input name="Investigation-prof" type="checkbox" />
                                    </li>
                                    <li>
                                        <label htmlFor="Medicine">Medicine<span className={styles.skill}>(Wis)</span></label>
                                        <input name="Medicine" placeholder="+0" type="text" /><input name="Medicine-prof" type="checkbox" />
                                    </li>
                                    <li>
                                        <label htmlFor="Nature">Nature<span className={styles.skill}>(Int)</span></label>
                                        <input name="Nature" placeholder="+0" type="text" /><input name="Nature-prof" type="checkbox" />
                                    </li>
                                    <li>
                                        <label htmlFor="Perception">Perception<span className={styles.skill}>(Wis)</span></label>
                                        <input name="Perception" placeholder="+0" type="text" /><input name="Perception-prof" type="checkbox" />
                                    </li>
                                    <li>
                                        <label htmlFor="PerhtmlFormance">PerhtmlFormance<span className={styles.skill}>(Cha)</span></label>
                                        <input name="PerhtmlFormance" placeholder="+0" type="text" /><input name="PerhtmlFormance-prof" type="checkbox" />
                                    </li>
                                    <li>
                                        <label htmlFor="Persuasion">Persuasion<span className={styles.skill}>(Cha)</span></label>
                                        <input name="Persuasion" placeholder="+0" type="text" /><input name="Persuasion-prof" type="checkbox" />
                                    </li>
                                    <li>
                                        <label htmlFor="Religion">Religion<span className={styles.skill}>(Int)</span></label>
                                        <input name="Religion" placeholder="+0" type="text" /><input name="Religion-prof" type="checkbox" />
                                    </li>
                                    <li>
                                        <label htmlFor="Sleight of Hand">Sleight of Hand<span className={styles.skill}>(Dex)</span></label>
                                        <input name="Sleight of Hand" placeholder="+0" type="text" /><input name="Sleight of Hand-prof" type="checkbox" />
                                    </li>
                                    <li>
                                        <label htmlFor="Stealth">Stealth<span className={styles.skill}>(Dex)</span></label>
                                        <input name="Stealth" placeholder="+0" type="text" /><input name="Stealth-prof" type="checkbox" />
                                    </li>
                                    <li>
                                        <label htmlFor="Survival">Survival<span className={styles.skill}>(Wis)</span></label>
                                        <input name="Survival" placeholder="+0" type="text" /><input name="Survival-prof" type="checkbox" />
                                    </li>
                                </ul>
                                <div className={styles.label}>
                                    Skills
                                </div>
                            </div>
                        </div>
                    </section>
                    <div className={`${styles.passivePerception} ${styles.box}`}>
                        <div className={styles.labelContainer}>
                            <label htmlFor="passiveperception">Passive Wisdom (Perception)</label>
                        </div>
                        <input name="passiveperception" placeholder="10" />
                    </div>
                    <div className={`${styles.otherprofs} ${styles.box} ${styles.textblock}`}>
                        <label htmlFor="otherprofs">Other Proficiencies and Languages</label><textarea name="otherprofs"></textarea>
                    </div>
                </section>
                <section>
                    <section className={styles.combat}>
                        <div className={styles.armorclass}>
                            <div>
                                <label htmlFor="ac">Armor className</label>
                                <input name="ac" placeholder="10" type="text" />
                            </div>
                        </div>
                        <div className={styles.initiative}>
                            <div>
                                <label htmlFor="initiative">Initiative</label>
                                <input name="initiative" placeholder="+0" type="text" />
                            </div>
                        </div>
                        <div className={styles.speed}>
                            <div>
                                <label htmlFor="speed">Speed</label>
                                <input name="speed" placeholder="30" type="text" />
                            </div>
                        </div>
                        <div className={styles.hp}>
                            <div className={styles.regular}>
                                <div className={styles.max}>
                                    <label htmlFor="maxhp">Hit Point Maximum</label>
                                    <input name="maxhp" placeholder="10" type="text" />
                                </div>
                                <div className={styles.current}>
                                    <label htmlFor="currenthp">Current Hit Points</label>
                                    <input name="currenthp" type="text" />
                                </div>
                            </div>
                            <div className={styles.temporary}>
                                <label htmlFor="temphp">Temporary Hit Points</label>
                                <input name="temphp" type="text" />
                            </div>
                        </div>
                        <div className={styles.hitdice}>
                            <div>
                                <div className={styles.total}>
                                    <label htmlFor="totalhd">Total</label>
                                    <input name="totalhd" placeholder="2d10" type="text" />
                                </div>
                                <div className={styles.remaining}>
                                    <label htmlFor="remaininghd">Hit Dice</label>
                                    <input name="remaininghd" type="text" />
                                </div>
                            </div>
                        </div>
                        <div className={styles.deathsaves}>
                            <div>
                                <div className={styles.label}>
                                    <label>Death Saves</label>
                                </div>
                                <div className={styles.marks}>
                                    <div className={styles.deathsuccesses}>
                                        <label>Successes</label>
                                        <div className={styles.bubbles}>
                                            <input name="deathsuccess1" type="checkbox" />
                                            <input name="deathsuccess2" type="checkbox" />
                                            <input name="deathsuccess3" type="checkbox" />
                                        </div>
                                    </div>
                                    <div className={styles.deathfails}>
                                        <label>Failures</label>
                                        <div className={styles.bubbles}>
                                            <input name="deathfail1" type="checkbox" />
                                            <input name="deathfail2" type="checkbox" />
                                            <input name="deathfail3" type="checkbox" />
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                    <section className={styles.attacksandspellcasting}>
                        <div>
                            <label>Attacks & Spellcasting</label>
                            <table>
                                <thead>
                                    <tr>
                                        <th>
                                            Name
                                        </th>
                                        <th>
                                            Atk Bonus
                                        </th>
                                        <th>
                                            Damage/Type
                                        </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>
                                            <input name="atkname1" type="text" />
                                        </td>
                                        <td>
                                            <input name="atkbonus1" type="text" />
                                        </td>
                                        <td>
                                            <input name="atkdamage1" type="text" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <input name="atkname2" type="text" />
                                        </td>
                                        <td>
                                            <input name="atkbonus2" type="text" />
                                        </td>
                                        <td>
                                            <input name="atkdamage2" type="text" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <input name="atkname3" type="text" />
                                        </td>
                                        <td>
                                            <input name="atkbonus3" type="text" />
                                        </td>
                                        <td>
                                            <input name="atkdamage3" type="text" />
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                            <textarea></textarea>
                        </div>
                    </section>
                    <section className={styles.equipment}>
                        <div>
                            <label>Equipment</label>
                            <div className={styles.money}>
                                <ul>
                                    <li>
                                        <label htmlFor="cp">cp</label><input name="cp" />
                                    </li>
                                    <li>
                                        <label htmlFor="sp">sp</label><input name="sp" />
                                    </li>
                                    <li>
                                        <label htmlFor="ep">ep</label><input name="ep" />
                                    </li>
                                    <li>
                                        <label htmlFor="gp">gp</label><input name="gp" />
                                    </li>
                                    <li>
                                        <label htmlFor="pp">pp</label><input name="pp" />
                                    </li>
                                </ul>
                            </div>
                            <textarea placeholder="Equipment list here"></textarea>
                        </div>
                    </section>
                </section>
                <section>
                    <section className={styles.flavor}>
                        <div className={styles.personality}>
                            <label htmlFor="personality">Personality</label><textarea name="personality"></textarea>
                        </div>
                        <div className={styles.ideals}>
                            <label htmlFor={styles.ideals}>Ideals</label><textarea name="ideals"></textarea>
                        </div>
                        <div className={styles.bonds}>
                            <label htmlFor="bonds">Bonds</label><textarea name="bonds"></textarea>
                        </div>
                        <div className={styles.flaws}>
                            <label htmlFor="flaws">Flaws</label><textarea name="flaws"></textarea>
                        </div>
                    </section>
                    <section className={styles.features}>
                        <div>
                            <label htmlFor={styles.features}>Features & Traits</label><textarea name="features"></textarea>
                        </div>
                    </section>
                </section>
            </div>
        </form>
    )
}