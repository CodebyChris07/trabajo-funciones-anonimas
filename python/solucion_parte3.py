from dataclasses import dataclass
from functools import reduce

@dataclass(frozen=True)
class Producto:
    nombre: str
    categoria: str
    precio: float
    stock: int

def main():
    inventario = [
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
    ]

    print("=== PARTE 3: SOLUCIONES EN PYTHON ===")

    # 1. SELECCIÓN (filter): Periféricos con stock > 25
    seleccionados = list(filter(lambda p: p.categoria == "Perifericos" and p.stock > 25, inventario))
    print("\n1. Seleccion (Perifericos con stock > 25):")
    for p in seleccionados:
        print(f"  - {p.nombre} (Stock: {p.stock})")

    # 2. TRANSFORMACIÓN (map): Etiqueta con precio + IVA (15%)
    transformados = list(map(lambda p: f"{p.nombre}: ${p.precio * 1.15:.2f}", inventario))
    print("\n2. Transformacion (Precios con IVA 15%):")
    for etiqueta in transformados:
        print(f"  - {etiqueta}")

    # 3. EXTREMOS (max y min con parámetro key): Producto más caro y menor stock
    mas_caro = max(inventario, key=lambda p: p.precio)
    menor_stock = min(inventario, key=lambda p: p.stock)
    print("\n3. Extremos (max / min con key):")
    print(f"  - Mas caro: {mas_caro.nombre} (${mas_caro.precio:.2f})")
    print(f"  - Menor stock: {menor_stock.nombre} ({menor_stock.stock} unidades)")

    # 4. ACUMULACIÓN (functools.reduce): Valor monetario total del inventario
    valor_total = reduce(lambda acum, p: acum + (p.precio * p.stock), inventario, 0.0)
    print("\n4. Acumulacion (functools.reduce):")
    print(f"  - Valor total del inventario: ${valor_total:.2f}")

if __name__ == "__main__":
    main()