//> using scala 3
package operaciones
case class Producto(nombre: String, categoria: String, precio: Double, stock: Int)

@main def ejecutarParte4Scala(): Unit =
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

  println("=== PARTE 4: CUATRO OPERACIONES NUEVAS Y COMBINACION ===")

  // Operación 1: groupBy (Agrupar productos por categoría)
  val porCategoria = inventario.groupBy(p => p.categoria)
  println("\n1. Operacion groupBy (Cantidad de modelos por categoria):")
  porCategoria.foreach((cat, lista) => println(s"  - $cat: ${lista.size} producto(s)"))

  // Operación 2: partition (Dividir entre gama alta > $100 y gama económica <= $100)
  val (gamaAlta, gamaEconomica) = inventario.partition(p => p.precio > 100.0)
  println("\n2. Operacion partition (Division por umbral de $100):")
  println(s"  - Gama Alta (${gamaAlta.size}): ${gamaAlta.map(p => p.nombre).mkString(", ")}")
  println(s"  - Gama Economica (${gamaEconomica.size}): ${gamaEconomica.map(p => p.nombre).mkString(", ")}")

  // Operación 3: sortBy (Ordenar inventario de menor a mayor stock)
  val ordenadosPorStock = inventario.sortBy(p => p.stock)
  println("\n3. Operacion sortBy (Top 3 productos con stock mas bajo):")
  ordenadosPorStock.take(3).foreach(p => println(s"  - ${p.nombre}: ${p.stock} unidades"))

  // Operación 4: find (Buscar el primer producto de Mobiliario con stock crítico < 10)
  val alertaMobiliario = inventario.find(p => p.categoria == "Mobiliario" && p.stock < 10)
  println("\n4. Operacion find (Primer mueble con stock critico < 10):")
  alertaMobiliario match
    case Some(p) => println(s"  - Encontrado: ${p.nombre} con ${p.stock} unidades")
    case None    => println("  - Sin alertas de stock en Mobiliario")

  // 4.3 COMBINAR OPERACIONES:
  // Pregunta: ¿Qué categorías tienen más de 50 unidades en stock total, ordenadas de mayor a menor?
  // Cadena: groupBy (nueva) -> map (vista en clase) -> filter (vista en clase) -> sortBy (nueva)
  val rankingCategorias = inventario
    .groupBy(p => p.categoria)
    .toList
    .map((cat, lista) => (cat, lista.foldLeft(0)((acc, p) => acc + p.stock)))
    .filter((cat, totalStock) => totalStock > 50)
    .sortBy((cat, totalStock) => -totalStock)

  println("\n5. Combinacion de operaciones (Categorias con stock total > 50 de mayor a menor):")
  rankingCategorias.foreach((cat, total) => println(s"  - $cat: $total unidades en total"))