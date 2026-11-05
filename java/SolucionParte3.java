import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class SolucionParte3 {

    public static class Producto {
        private final String nombre;
        private final String categoria;
        private final double precio;
        private final int stock;

        public Producto(String nombre, String categoria, double precio, int stock) {
            this.nombre = nombre;
            this.categoria = categoria;
            this.precio = precio;
            this.stock = stock;
        }

        public String getNombre() { 
            return nombre; 
        }
        public String getCategoria() { 
            return categoria; 
        }
        public double getPrecio() { 
            return precio; 
        }
        public int getStock() { 
            return stock; 
        }
    }

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);

        List<Producto> inventario = List.of(
            new Producto("Laptop Pro", "Computacion", 1250.00, 8),
            new Producto("Mouse Inalambrico", "Perifericos", 25.50, 45),
            new Producto("Teclado Mecanico", "Perifericos", 85.00, 30),
            new Producto("Monitor 27 Pulgadas", "Monitores", 320.00, 12),
            new Producto("Cable HDMI 2m", "Accesorios", 12.00, 100),
            new Producto("Disco SSD 1TB", "Almacenamiento", 95.00, 25),
            new Producto("Memoria RAM 16GB", "Componentes", 60.00, 40),
            new Producto("Audifonos Studio", "Audio", 150.00, 18),
            new Producto("Webcam Full HD", "Perifericos", 55.00, 22),
            new Producto("Silla Ergonomica", "Mobiliario", 280.00, 5),
            new Producto("Hub USB-C", "Accesorios", 35.00, 60),
            new Producto("Microfono Condensador", "Audio", 110.00, 15)
        );

        System.out.println("=== PARTE 3: SOLUCIONES EN JAVA ===");

        // 1. SELECCIÓN (filter)
        List<Producto> seleccionados = inventario.stream()
            .filter(p -> p.getCategoria().equals("Perifericos") && p.getStock() > 25)
            .collect(Collectors.toList());
        System.out.println("\n1. Seleccion (Perifericos con stock > 25):");
        seleccionados.forEach(p -> System.out.println("  - " + p.getNombre() + " (Stock: " + p.getStock() + ")"));

        // 2. TRANSFORMACIÓN (map)
        List<String> transformados = inventario.stream()
            .map(p -> String.format("%s: $%.2f", p.getNombre(), p.getPrecio() * 1.15))
            .collect(Collectors.toList());
        System.out.println("\n2. Transformacion (Precios con IVA 15%):");
        transformados.forEach(etiqueta -> System.out.println("  - " + etiqueta));

        // 3. EXTREMOS (max / min)
        Producto masCaro = inventario.stream()
            .max(Comparator.comparingDouble(p -> p.getPrecio()))
            .orElseThrow();
        Producto menorStock = inventario.stream()
            .min(Comparator.comparingInt(p -> p.getStock()))
            .orElseThrow();
        System.out.println("\n3. Extremos (max / min):");
        System.out.printf("  - Mas caro: %s ($%.2f)%n", masCaro.getNombre(), masCaro.getPrecio());
        System.out.printf("  - Menor stock: %s (%d unidades)%n", menorStock.getNombre(), menorStock.getStock());

        // 4. ACUMULACIÓN (reduce)
        double valorTotal = inventario.stream()
            .reduce(0.0, (acum, p) -> acum + (p.getPrecio() * p.getStock()), Double::sum);
        System.out.println("\n4. Acumulacion (reduce):");
        System.out.printf("  - Valor total del inventario: $%.2f%n", valorTotal);
    }
}