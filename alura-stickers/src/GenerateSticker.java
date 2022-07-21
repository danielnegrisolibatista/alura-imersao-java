import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GenerateSticker {
  
  public void create(InputStream inputStream, String folderName, String fileName, String stickerText) throws Exception {
    BufferedImage imagemOriginal = ImageIO.read(inputStream);

    // cria nova imagem em memória com transparência e com tamanho novo
    int width = imagemOriginal.getWidth();
    int height = imagemOriginal.getHeight();
    int newHeight = height + 200;
    BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);

    // copiar a imagem original pra novo imagem (em memória)
    Graphics2D graphics = (Graphics2D) newImage.getGraphics();
    graphics.drawImage(imagemOriginal, 0, 0, null);

    // configurar a fonte
    var textFont = new Font(Font.SANS_SERIF, Font.BOLD, 64);
    graphics.setColor(Color.YELLOW);
    graphics.setFont(textFont);

    // escrever uma frase na nova imagem
    graphics.drawString(stickerText, 100, newHeight - 100);

    // escrever a nova imagem em um arquivo
    File sticker = new File(folderName+"/"+fileName);
    sticker.mkdirs();

    ImageIO.write(newImage, "png", sticker);

    System.out.println("Figurinha gerada: " + fileName);
  }
}
