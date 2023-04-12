import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

public class ExerciseTest {
    public static int numeroAleatorio(int minimo, int maximo) {
        return ThreadLocalRandom.current().nextInt(minimo, maximo + 1);
    }
    public static String textosAleatoreos(int longitud) {
        String banco = "abcdefghijklmnopqrstuvwxyz";
        String cadena = "";
        for (int i = 0; i < longitud; i++) {
            int indiceAleatorio = numeroAleatorio(0, banco.length() - 1);
            char caracterAleatorio = banco.charAt(indiceAleatorio);
            cadena += caracterAleatorio;
        }
        return cadena;
    }
    public static String paisAleatoreo(int longitud){
        String pais = textosAleatoreos(longitud);
        switch (pais){
            case "ca":
                break;
            case "ar":
                break;
            case "bo":
                break;
            case "br":
                break;
            case "co":
                break;
            case "de":
                break;
            case "gt":
                break;
            case "ni":
                break;
            case "pr":
                break;
            case "mx":
                break;
            default:
                pais = "us";
        }
        return pais;
    }
    @Test
    public void test(){
        try{
            // Configuracion del webdriver
            WebDriverManager.chromedriver().setup();
            WebDriver drive = new ChromeDriver();
            drive.manage().window().maximize();
            JavascriptExecutor executor = (JavascriptExecutor) drive;
            // Ingresar al sitio web
            drive.get("https://www.crimsoncircle.com/");
            // Dar click en el boton Enter
            drive.findElement(By.xpath("//a[@id='dnn_ctr2638_View_linkLearnMore']")).click();
            // Dar click en el boton More
            drive.findElement(By.xpath("//a[@id='dnn_MenuHeader_Menu_rptMenu_link_6']")).click();
            // Buscar y dar click en el boton de Sign In with Email
            WebElement signInButton = drive.findElement(By.xpath("//a[normalize-space()='View Sign Up for E-Mail List']"));
            executor.executeScript("arguments[0].scrollIntoView(true);", signInButton);
            signInButton.click();
            // Hacemos que el programa espere 5 segundo
            Thread.sleep(5000);
            // Ingresamos el dato del correo electronico
            drive.findElement(By.xpath("//input[@id='inputProp0']")).sendKeys(textosAleatoreos(10) + "@gmail.com");
            // Ingresamos el dato del nombre del usuario
            drive.findElement(By.xpath("//input[@id='inputProp1']")).sendKeys(textosAleatoreos(12));;
            // Ingresamos el dato del apellido del usuario
            drive.findElement(By.xpath("//input[@id='inputProp2']")).sendKeys(textosAleatoreos(12));
            // Seleccionamos el dato del pais del usuario
            WebElement countrySelect = drive.findElement(By.xpath("//select[@id='inputProp3']"));
            Select select = new Select(countrySelect);
            select.selectByValue(paisAleatoreo(2));
            // Damos click en el boton de submit del formulario
            drive.findElement(By.xpath("//input[@id='update-profile-submit-btn']")).click();
            // Capturamos e imprimimos el mensaje de exito a la suscripcion
            System.out.println(drive.findElement(By.xpath("//div[@id='optinSuccess']")).getText());
        } catch (Exception e){
            System.out.println("El error es: " + e);
        }
    }
}