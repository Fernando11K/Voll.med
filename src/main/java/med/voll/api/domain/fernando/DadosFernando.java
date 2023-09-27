package med.voll.api.domain.fernando;

import java.time.LocalDate;

public record DadosFernando(String numero, String data) {


    public LocalDate getDataFormatada(){


        if (!this.data.isBlank() && this.data != null){
         return LocalDate.parse(this.data);
        }
        return null;
    }
}

