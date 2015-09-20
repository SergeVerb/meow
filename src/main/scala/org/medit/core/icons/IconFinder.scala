package org.medit.core.icons

import java.awt.Image
import java.io.File
import javax.swing.{ImageIcon, JLabel}

import org.medit.gui.utils.Colors

import scala.collection._


object IconFinder {
  val missing = IconGenerator.generateIcon("No icon found", Colors.lightGray)
    // ImageIO.read(Main.getFile("/missing.png"))
  val iconCache = mutable.HashMap[String, Image]()
  val iconCacheWithSize = mutable.HashMap[(String, Int), ImageIcon]()

  def getIcon(nameBase: String, size: Int = 64) = {
    // Build the scaled image icon
    iconCacheWithSize.getOrElseUpdate((nameBase, size), {
      new ImageIcon(loadIcon(nameBase, size).getScaledInstance(size, size, Image.SCALE_SMOOTH))
    })
  }

  private def loadIcon(nameBase: String, size: Int = 64) : Image = {
    val iconName = nameBase.toLowerCase
    val iconFile = new File(nameBase)

    // Seems to be a path to a file, try to read the file
    if (iconFile.isFile) {
      for(img <- ImageLoader.get(nameBase)) { iconCache(iconName) = img } }

    iconCache.getOrElseUpdate(iconName, {
      (for(icon <- IconLibrary.icons.get(iconName); image <- icon.getIcon) yield {
        image
      }).getOrElse(missing)
    })
  }

  def getIconLoader(nameBase: String, size: Int = 64, label : JLabel) : ImageIcon = {
    val baseIcon = loadIcon(nameBase, size)
    val scaler = new ImageScaler(baseIcon, size, label)
    scaler.setPriority(Thread.MIN_PRIORITY)
    scaler.start()
    new ImageIcon(baseIcon)
  }
}
