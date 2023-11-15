import java.sql.{Connection, DriverManager, ResultSet}
import java.awt.event.{ActionEvent, ActionListener}
import javax.swing.{JButton, JFrame, JPanel, SwingUtilities, JLabel, JTextField, JScrollPane, JTextArea}
import java.awt.{Dimension, Color}

object main {
  
  def main(args: Array[String]): Unit = {
    
    SwingUtilities.invokeLater(() => {
      
      val frame = new JFrame("Lab 5")
      frame.setSize(520, 570)
      frame.setLayout(null)
      frame.getContentPane().setBackground(Color.CYAN)
      frame.setLocationRelativeTo(null)
      
      val productLabel = new JLabel("Product")
      productLabel.setForeground(Color.BLACK)
      val priceLabel = new JLabel("Price")
      productLabel.setForeground(Color.BLACK)
      val quantityLabel = new JLabel("Quantity")
      productLabel.setForeground(Color.BLACK)
      val moreThanLabel = new JLabel("More Than")
      productLabel.setForeground(Color.BLACK)
      val resultLabel = new JLabel("Result from Sklad")
      resultLabel.setForeground(Color.BLACK)
      
      productLabel.setBounds(10, 0, 120, 20)
      priceLabel.setBounds(130, 0, 120, 20)
      quantityLabel.setBounds(250, 0, 120, 20)
      moreThanLabel.setBounds(370, 0, 120, 20)
      resultLabel.setBounds(10, 190, 120, 20)
      
      val productField = new JTextField(20)
      val priceField = new JTextField(20)
      val quantityField = new JTextField(20)
      val moreThanField = new JTextField(20)
      val resultField = new JTextArea()
      
      productField.setBounds(10, 20, 120, 50)
      priceField.setBounds(130, 20, 120, 50)
      quantityField.setBounds(250, 20, 120, 50)
      moreThanField.setBounds(370, 20, 120, 50)
      resultField.setBounds(10, 210, 480, 300)
      resultField.setEditable(false);
      
      val insertButton = new JButton("Insert")
      insertButton.setBackground(Color.BLACK)
      insertButton.setForeground(Color.WHITE)
      val selectButton = new JButton("Select *")
      selectButton.setBackground(Color.BLACK)
      selectButton.setForeground(Color.WHITE)
      val getMaxButton = new JButton("Get Max")
      getMaxButton.setBackground(Color.BLACK)
      getMaxButton.setForeground(Color.WHITE)
      val getMoreThanButton = new JButton("Get More Than")
      getMoreThanButton.setBackground(Color.BLACK)
      getMoreThanButton.setForeground(Color.WHITE)
      val getMaxPriceButton = new JButton("Get Max Price")
      getMaxPriceButton.setBackground(Color.MAGENTA)
      getMaxPriceButton.setForeground(Color.WHITE)
      val getMaxGroupButton = new JButton("Get Max GroupBy")
      getMaxGroupButton.setBackground(Color.BLACK)
      getMaxGroupButton.setForeground(Color.WHITE)
      val getAvgPriceButton = new JButton("Get Avg Price")
      getAvgPriceButton.setBackground(Color.BLACK)
      getAvgPriceButton.setForeground(Color.WHITE)
      
      insertButton.setBounds(10, 80, 120, 50)
      selectButton.setBounds(130, 80, 120, 50)
      getMaxButton.setBounds(250, 80, 120, 50)
      getMoreThanButton.setBounds(370, 80, 120, 50)
      getMaxPriceButton.setBounds(10, 130, 120, 50)
      getMaxGroupButton.setBounds(130, 130, 120, 50)
      getAvgPriceButton.setBounds(250, 130, 120, 50)
      
      frame.add(insertButton)
      frame.add(selectButton)
      frame.add(getMaxButton)
      frame.add(getMoreThanButton)
      frame.add(getMaxPriceButton)
      frame.add(getMaxGroupButton)
      frame.add(getAvgPriceButton)
      
      frame.add(productField)
      frame.add(priceField)
      frame.add(quantityField)
      frame.add(moreThanField)
      frame.add(resultField)
      
      frame.add(productLabel)
      frame.add(priceLabel)
      frame.add(quantityLabel)
      frame.add(moreThanLabel)
      frame.add(resultLabel)
      
      insertButton.addActionListener(new ActionListener{
        override def actionPerformed(e: ActionEvent): Unit = {
          resultField.setText("")
          val url = "jdbc:mysql://localhost:3309/mydb1"
          val driver = "com.mysql.jdbc.Driver"
          val username = "root"
          val password = "12345"
          Class.forName(driver)
          val connection: Connection = DriverManager.getConnection(url, username, password)
          
          try {

            val statement = connection.createStatement()
            val rs = statement.execute("INSERT INTO sklad VALUES ('" + productField.getText() + "'," + priceField.getText() + "," + quantityField.getText() + ")")

            productField.setText("")
            priceField.setText("")
            quantityField.setText("")
            
          } catch {
            case e: Exception => {
              resultField.setText("ERROR")
              println("ERROR")
            } 
          } finally {
            connection.close()
          }
        }
      })
      
      selectButton.addActionListener(new ActionListener{
        override def actionPerformed(e: ActionEvent): Unit = {
          resultField.setText("")
          val url = "jdbc:mysql://localhost:3309/mydb1"
          val driver = "com.mysql.jdbc.Driver"
          val username = "root"
          val password = "12345"
          Class.forName(driver)
          val connection: Connection = DriverManager.getConnection(url, username, password)
          
          try {

            val statement = connection.createStatement()
            val rs = statement.executeQuery("SELECT * FROM sklad")
            
            println("Sklad:")
            while (rs.next()) {
              val product = rs.getString("product")
              val price = rs.getInt("price")
              val quantity = rs.getInt("quantity")
              resultField.setText(resultField.getText + "\n" + s"product: $product, price: $price, quantity: $quantity")
              println(s"product: $product, price: $price, quantity: $quantity")
            }
            println("")     
            
          } catch {
            case e: Exception => {
              resultField.setText("ERROR")
              println("ERROR")
            } 
          } finally {
            connection.close()
          }
        }
      })
      
      getMoreThanButton.addActionListener(new ActionListener{
        override def actionPerformed(e: ActionEvent): Unit = {
          resultField.setText("")
          val url = "jdbc:mysql://localhost:3309/mydb1"
          val driver = "com.mysql.jdbc.Driver"
          val username = "root"
          val password = "12345"
          Class.forName(driver)
          val connection: Connection = DriverManager.getConnection(url, username, password)
          
          try {

            val statement = connection.createStatement()
            val minPrice = moreThanField.getText()
            val rs = statement.executeQuery("SELECT * FROM sklad WHERE price > " + minPrice)
            
            println(s"Sklad More Than $minPrice:")
            while (rs.next()) {
              val product = rs.getString("product")
              val price = rs.getInt("price")
              val quantity = rs.getInt("quantity")
              resultField.setText(resultField.getText + "\n" + s"product: $product, price: $price, quantity: $quantity")
              println(s"product: $product, price: $price, quantity: $quantity")
            }
            println("")   
            moreThanField.setText("")
          } catch {
            case e: Exception => {
              resultField.setText("ERROR")
              println("ERROR")
            }
          } finally {
            connection.close()
          }
        }
      })
      
      getMaxButton.addActionListener(new ActionListener{
        override def actionPerformed(e: ActionEvent): Unit = {
          resultField.setText("")
          val url = "jdbc:mysql://localhost:3309/mydb1"
          val driver = "com.mysql.jdbc.Driver"
          val username = "root"
          val password = "12345"
          Class.forName(driver)
          val connection: Connection = DriverManager.getConnection(url, username, password)
          
          try {

            val statement = connection.createStatement()
            val rsm = statement.executeQuery("SELECT MAX(price * quantity) AS max FROM sklad")
            
            var max: Int = 0
            rsm.next()
            max = rsm.getInt("max")
            
            val rs = statement.executeQuery("SELECT * FROM sklad WHERE price * quantity = " + max)
            
            println(s"Sklad Max Income ($max$$):")
            while (rs.next()) {
              val product = rs.getString("product")
              val price = rs.getInt("price")
              val quantity = rs.getInt("quantity")
              val income = quantity * price
              resultField.setText(resultField.getText + "\n" + s"product: $product, price: $price, quantity: $quantity, income: $income$$")
              println(s"product: $product, price: $price, quantity: $quantity, income: $income$$")
            }
            println("")
            
          } catch {
            case e: Exception => {
              resultField.setText("ERROR")
              println("ERROR")
            }
          } finally {
            connection.close()
          }
        }
      })
      
      getMaxGroupButton.addActionListener(new ActionListener{
        override def actionPerformed(e: ActionEvent): Unit = {
          resultField.setText("")
          val url = "jdbc:mysql://localhost:3309/mydb1"
          val driver = "com.mysql.jdbc.Driver"
          val username = "root"
          val password = "12345"
          Class.forName(driver)
          val connection: Connection = DriverManager.getConnection(url, username, password)
          
          try {

            val statement = connection.createStatement()
            val rs = statement.executeQuery("SELECT MAX(price * quantity) as maxincome FROM sklad")
            //val rs = statement.executeQuery("SELECT * FROM sklad GROUP BY price * quantity")

            while (rs.next()) {
              val maxinc = rs.getInt("maxincome")
              resultField.setText(resultField.getText + "\n" + s"max price: $maxinc")
              //val product = rs.getString("product")
              //val price = rs.getInt("price")
              //val quantity = rs.getInt("quantity")
              //val income = quantity * price
              //resultField.setText(resultField.getText + "\n" + s"product: $product, price: $price, quantity: $quantity, income: $income$$")
              //println(s"product: $product, price: $price, quantity: $quantity, income: $income$$")
            }
            println("")
            
          } catch {
            case e: Exception => {
              resultField.setText("ERROR")
              println("ERROR")
            }
          } finally {
            connection.close()
          }
        }
      })
      
      getMaxPriceButton.addActionListener(new ActionListener{
        override def actionPerformed(e: ActionEvent): Unit = {
          resultField.setText("")
          val url = "jdbc:mysql://localhost:3309/mydb1"
          val driver = "com.mysql.jdbc.Driver"
          val username = "root"
          val password = "12345"
          Class.forName(driver)
          val connection: Connection = DriverManager.getConnection(url, username, password)
          
          try {

            val statement = connection.createStatement()
            val rs = statement.executeQuery("SELECT * FROM sklad WHERE price >= ALL(SELECT price FROM sklad)")
            
            while (rs.next()) {
              val product = rs.getString("product")
              val price = rs.getInt("price")
              val quantity = rs.getInt("quantity")
              val income = quantity * price
              resultField.setText(resultField.getText + "\n" + s"product: $product, price: $price, quantity: $quantity, income: $income$$")
              println(s"product: $product, price: $price, quantity: $quantity, income: $income")
            }
            println("")
            
          } catch {
            case e: Exception => {
              resultField.setText("ERROR")
              println("ERROR")
            }
          } finally {
            connection.close()
          }
        }
      })
      
      getAvgPriceButton.addActionListener(new ActionListener{
        override def actionPerformed(e: ActionEvent): Unit = {
          resultField.setText("")
          val url = "jdbc:mysql://localhost:3309/mydb1"
          val driver = "com.mysql.jdbc.Driver"
          val username = ""
          val password = ""
          Class.forName(driver)
          val connection: Connection = DriverManager.getConnection(url, username, password)
          
          try {

            val statement = connection.createStatement()
            val result = statement.executeQuery("SELECT AVG(price) AS average_price FROM sklad GROUP BY product HAVING AVG(price) > 100")
            while (result.next()) {
              val avgprice = result.getDouble("average_price")
              resultField.setText(resultField.getText + "\n" + "Avg Price: " + avgprice)
              println("Avg Price: " + avgprice)
            }
            println("")
            
          } catch {
            case e: Exception => {
              resultField.setText("ERROR")
              println("ERROR")
            }
          } finally {
            connection.close()
          }
        }
      })
      
      frame.setVisible(true)
      
    })
    
  }
}
