//> using scala 3
case class Producto(nombre: String, categoria: String, precio: Double, stock: Int)

@main def ejecutarParte3Scala(): Unit =
  val inventario: List[Producto] = List(
    Producto("Laptop Pro", "Computacion", 1250.00, 8),
    Producto("Mouse Inalambrico", "Perifericos", 25.50, 45),
    Producto("Teclado Mecanico", "Perifericos", 85.00, 30),
    Producto("Monitor 27 Pulgadas", "Monitores", 320.00, 12),
    Producto("Cable HDMI 2m", "Accesorios", 12.00, 100),
    Producto("Disco SSD 1TB", "Almacenamiento", 95.00, 25),
    Producto("Memoria RAM 16GB", "Componentes", 60.00, 40),
    Producto("Audifonos Studio", "Audio", 150.00, 18),
    Producto("Webcam Full HD", "Perifericos", 55.00, 22),
    Producto("Silla Ergonomica", "Mobiliario", 280.00, 5),
    Producto("Hub USB-C", "Accesorios", 35.00, 60),
    Producto("Microfono Condensador", "Audio", 110.00, 15)
  )

  println("=== PARTE 3: SOLUCIONES EN SCALA ===")

  // 1. SELECCIÓN (filter): Periféricos con stock > 25
  val seleccionados = inventario.filter(p => p.categoria == "Perifericos" && p.stock > 25)
  println("\n1. Seleccion (Perifericos con stock > 25):")
  seleccionados.foreach(p => println(f"  - ${p.nombre} (Stock: ${p.stock})"))

  // 2. TRANSFORMACIÓN (map): Etiqueta con precio + IVA (15%)
  val transformados = inventario.map(p => f"${p.nombre}: $$${p.precio * 1.15}%.2f")
  println("\n2. Transformacion (Precios con IVA 15%):")
  transformados.foreach(etiqueta => println(s"  - $etiqueta"))

  // 3. EXTREMOS (maxBy y minBy): Producto más caro y producto con menor stock
  val masCaro = inventario.maxBy(p => p.precio)
  val menorStock = inventario.minBy(p => p.stock)
  println("\n3. Extremos (maxBy / minBy):")
  println(f"  - Mas caro: ${masCaro.nombre} ($$${masCaro.precio}%.2f)")
  println(s"  - Menor stock: ${menorStock.nombre} (${menorStock.stock} unidades)")

  // 4. ACUMULACIÓN (foldLeft): Valor monetario total del inventario
  val valorTotal = inventario.foldLeft(0.0)((acum, p) => acum + (p.precio * p.stock))
  println("\n4. Acumulacion (foldLeft):")
  println(f"  - Valor total del inventario: $$$valorTotal%.2f")