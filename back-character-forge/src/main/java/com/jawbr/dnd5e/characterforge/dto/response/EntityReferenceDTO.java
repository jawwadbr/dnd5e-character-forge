package com.jawbr.dnd5e.characterforge.dto.response;

import lombok.Builder;

/**
 * This DTO serves as an Entity Reference for cases where one entity is associated with another entity.
 * <p>
 * For instance, an Ability Score such as Dexterity may have a collection of associated skills like Acrobatics and Stealth.
 * These skills are represented as Entity References within this context.
 *
 * @author <a href="https://www.linkedin.com/in/bradley-sperling/">Bradley Jawwad</a>
 */
@Builder
public record EntityReferenceDTO(
        String index,
        String name,
        String url
) {
}
