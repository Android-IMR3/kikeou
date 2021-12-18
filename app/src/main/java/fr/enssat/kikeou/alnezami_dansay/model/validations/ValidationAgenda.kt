package fr.enssat.kikeou.alnezami_dansay.model.validations

import fr.enssat.kikeou.alnezami_dansay.model.entity.Agenda
import fr.enssat.kikeou.alnezami_dansay.model.entity.Contact
import fr.enssat.kikeou.alnezami_dansay.model.entity.LOC
import io.konform.validation.Validation
import io.konform.validation.jsonschema.maxLength
import io.konform.validation.jsonschema.maximum
import io.konform.validation.jsonschema.minLength
import io.konform.validation.jsonschema.minimum


val validateAgenda = Validation<Agenda> {
    Agenda::name {
        minLength(2)
        maxLength(40)
    }

    Agenda::week ifPresent {
        minimum(0) hint "Attention au format de date dd/mm/aaaa"
        maximum(150) hint "Attention au format de date dd/mm/aaaa"
    }
    Agenda::loc  onEach{
       LOC::day ifPresent{
           minimum(0)
           maximum(6)
       }
        LOC::value ifPresent{
            minLength(3)
            maxLength(20)
        }
    }

    Agenda::contact  onEach{
        Contact::key ifPresent{
            minLength(3)
            maxLength(150)
        }
        Contact::value ifPresent{
            minLength(5)
            maxLength(150)
        }
    }

}
