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
    @CsvSource({ "1111111,SI APLICA A BECA", 							// Si aplica beca
          "2222222,NO APLICA A BECA POR PROMEDIO ACADEMICO",			//No aplica beca
          "3333333,EL ESTUDIANTE NO CURSO AUN EL 60% DE LAS MATERIAS",	//Materias insufisientes
    })
    public void recomendacionBecaTest(int ci, String expectedResult) {
    	Mockito.when(utilsMock.getNota(1111111)).thenReturn(91);
    	
    	
    	Mockito.when(utilsMock.getNota(2222222)).thenReturn(70);
    	
    	
    	Mockito.when(utilsMock.getNota(3333333)).thenReturn(100);
    	
    	
    	
    	helpersStatic.when(()-> Helpers.applicaBeca(1111111)).thenReturn(true);
    	
    	helpersStatic.when(()-> Helpers.applicaBeca(2222222)).thenReturn(true);

    	helpersStatic.when(()-> Helpers.applicaBeca(3333333)).thenReturn(false);
    	
    	Beca beca = new Beca(utilsMock);
    	
    	String actualResult = beca.recomendacionBeca(ci);
    	
        Assertions.assertEquals(expectedResult,actualResult,"ERROR el resultado es incorrecto");


    	

    }
}
