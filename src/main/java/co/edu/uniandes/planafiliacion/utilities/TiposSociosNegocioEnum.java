package co.edu.uniandes.planafiliacion.utilities;

import lombok.Getter;

@Getter
public enum TiposSociosNegocioEnum {
	
	ENTRENADOR_PERSONALIZADO("ENTRENADOR PERSONALIZADO"), 
	MEDICO_DEPORTOLOGO("MEDICO DEPORTOLOGO"), 
	PRESTADOR_SERVICIO("PRESTADOR DE SERVICIO"), 
	ORGANIZADOR_EVENTOS("ORGANIZADOR DE EVENTOS");
	
	String tipo;
	
	private TiposSociosNegocioEnum(String tipo) {
		this.tipo = tipo;
	}

}
