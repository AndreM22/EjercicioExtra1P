package ejercicioExtraTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import ejercicioExtra.Beca;
import ejercicioExtra.Helpers;
import ejercicioExtra.Utils;

public class BecaTest {
	
	Utils utilsMock = Mockito.mock(Utils.class);
    
	MockedStatic<Helpers> helpersStatic= Mockito.mockStatic(Helpers.class);


    @ParameterizedTest
    @CsvSource({ "100,SI APLICA A BECA", 							// Si aplica beca
    	"101,SI APLICA A BECA",
    	"102,SI APLICA A BECA",
    	
        "200,NO APLICA A BECA POR PROMEDIO ACADEMICO",			//No aplica beca
        "201,NO APLICA A BECA POR PROMEDIO ACADEMICO",
        "202,NO APLICA A BECA POR PROMEDIO ACADEMICO",
        
        
        "300,EL ESTUDIANTE NO CURSO AUN EL 60% DE LAS MATERIAS",	//Materias insufisientes
        "301,EL ESTUDIANTE NO CURSO AUN EL 60% DE LAS MATERIAS",
        "302,EL ESTUDIANTE NO CURSO AUN EL 60% DE LAS MATERIAS",
    })
    public void recomendacionBecaTest(int ci, String expectedResult) {
    	
    	
    	Mockito.when(utilsMock.getNota(100)).thenReturn(91);
    	Mockito.when(utilsMock.getNota(101)).thenReturn(92);
    	Mockito.when(utilsMock.getNota(102)).thenReturn(100);
    	
    	
    	Mockito.when(utilsMock.getNota(200)).thenReturn(70);
    	Mockito.when(utilsMock.getNota(201)).thenReturn(70);
    	Mockito.when(utilsMock.getNota(202)).thenReturn(70);
    	
    	
    	Mockito.when(utilsMock.getNota(300)).thenReturn(100);
    	Mockito.when(utilsMock.getNota(301)).thenReturn(0);
    	Mockito.when(utilsMock.getNota(302)).thenReturn(50);
    	
    	
    	helpersStatic.when(()-> Helpers.applicaBeca(100)).thenReturn(true);
    	
    	helpersStatic.when(()-> Helpers.applicaBeca(200)).thenReturn(true);

    	helpersStatic.when(()-> Helpers.applicaBeca(300)).thenReturn(false);
    	
    	Beca beca = new Beca(utilsMock);
    	
    	String actualResult = beca.recomendacionBeca(ci);
    	
        Assertions.assertEquals(expectedResult,actualResult,"ERROR el resultado es incorrecto");

        
        
        helpersStatic.close();
    	

    }
}
