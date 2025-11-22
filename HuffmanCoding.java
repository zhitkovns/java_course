import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

// Класс для узла дерева Хаффмана
class HuffmanNode implements Comparable<HuffmanNode> {
    byte character;
    int frequency;
    HuffmanNode left, right;
    
    HuffmanNode(byte character, int frequency) {
        this.character = character;
        this.frequency = frequency;
        this.left = null;
        this.right = null;
    }
    
    HuffmanNode(int frequency, HuffmanNode left, HuffmanNode right) {
        this.character = -1;
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }
    
    boolean isLeaf() {
        return left == null && right == null;
    }
    
    @Override
    public int compareTo(HuffmanNode other) {
        return Integer.compare(this.frequency, other.frequency);
    }
}

// Основной класс для кодирования/декодирования Хаффмана
public class HuffmanCoding {
    
    // Кодирование файла
    public static void encode(String inputFile, String outputFile) throws IOException {
        // Чтение всего файла сразу
        byte[] fileData = readEntireFile(inputFile);
        
        // Подсчет частот
        Map<Byte, Integer> frequencyMap = calculateFrequencies(fileData);
        
        // Построение дерева Хаффмана
        HuffmanNode root = buildHuffmanTree(frequencyMap);
        
        // Генерация кодов
        Map<Byte, String> huffmanCodes = generateCodes(root);
        
        // Кодирование данных
        String encodedBits = encodeData(fileData, huffmanCodes);
        
        // Сохранение закодированных данных и таблицы кодирования
        saveEncodedData(encodedBits, huffmanCodes, outputFile);
        
        System.out.println("Encoding completed:");
        System.out.println("  Input: " + inputFile);
        System.out.println("  Output: " + outputFile);
        System.out.println("  Original size: " + fileData.length + " bytes");
        System.out.println("  Compressed size: " + (int)Math.ceil(encodedBits.length() / 8.0) + " bytes");
        System.out.println("  Compression ratio: " + String.format("%.2f", 
            (1 - (Math.ceil(encodedBits.length() / 8.0) / (double)fileData.length)) * 100) + "%");
        
        // Вывод таблицы кодирования
        System.out.println("\nHuffman codes:");
        List<Map.Entry<Byte, String>> sortedCodes = huffmanCodes.entrySet()
            .stream()
            .sorted((a, b) -> Integer.compare(a.getKey(), b.getKey()))
            .collect(Collectors.toList());
            
        for (Map.Entry<Byte, String> entry : sortedCodes) {
            byte b = entry.getKey();
            String code = entry.getValue();
            if (b >= 32 && b <= 126) {
                System.out.println("  '" + (char)b + "' (0x" + String.format("%02X", b) + ") -> " + code);
            } else {
                System.out.println("  0x" + String.format("%02X", b) + " -> " + code);
            }
        }
    }
    
    // Декодирование файла
    public static void decode(String inputFile, String outputFile) throws IOException {
        // Чтение закодированного файла
        EncodedData encodedData = readEncodedFile(inputFile);
        
        // Декодирование данных
        byte[] decodedData = decodeData(encodedData.encodedBits, encodedData.huffmanTree);
        
        // Сохранение декодированных данных
        writeEntireFile(outputFile, decodedData);
        
        System.out.println("Decoding completed:");
        System.out.println("  Input: " + inputFile);
        System.out.println("  Output: " + outputFile);
        System.out.println("  Decoded size: " + decodedData.length + " bytes");
    }
    
    // Вспомогательные методы
    
    private static byte[] readEntireFile(String filename) throws IOException {
        File file = new File(filename);
        byte[] data = new byte[(int)file.length()];
        try (FileInputStream fis = new FileInputStream(file)) {
            fis.read(data);
        }
        return data;
    }
    
    private static void writeEntireFile(String filename, byte[] data) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(filename)) {
            fos.write(data);
        }
    }
    
    private static Map<Byte, Integer> calculateFrequencies(byte[] data) {
        Map<Byte, Integer> frequencyMap = new HashMap<>();
        for (byte b : data) {
            frequencyMap.put(b, frequencyMap.getOrDefault(b, 0) + 1);
        }
        return frequencyMap;
    }
    
    private static HuffmanNode buildHuffmanTree(Map<Byte, Integer> frequencyMap) {
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>();
        
        // Создаем листья для каждого символа
        for (Map.Entry<Byte, Integer> entry : frequencyMap.entrySet()) {
            pq.offer(new HuffmanNode(entry.getKey(), entry.getValue()));
        }
        
        // Строим дерево
        while (pq.size() > 1) {
            HuffmanNode left = pq.poll();
            HuffmanNode right = pq.poll();
            HuffmanNode parent = new HuffmanNode(left.frequency + right.frequency, left, right);
            pq.offer(parent);
        }
        
        return pq.poll();
    }
    
    private static Map<Byte, String> generateCodes(HuffmanNode root) {
        Map<Byte, String> codes = new HashMap<>();
        generateCodesRecursive(root, "", codes);
        return codes;
    }
    
    private static void generateCodesRecursive(HuffmanNode node, String code, Map<Byte, String> codes) {
        if (node == null) return;
        
        if (node.isLeaf()) {
            codes.put(node.character, code.isEmpty() ? "0" : code);
        } else {
            generateCodesRecursive(node.left, code + "0", codes);
            generateCodesRecursive(node.right, code + "1", codes);
        }
    }
    
    private static String encodeData(byte[] data, Map<Byte, String> huffmanCodes) {
        StringBuilder encodedBits = new StringBuilder();
        for (byte b : data) {
            encodedBits.append(huffmanCodes.get(b));
        }
        return encodedBits.toString();
    }
    
    private static void saveEncodedData(String encodedBits, Map<Byte, String> huffmanCodes, String outputFile) 
            throws IOException {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(outputFile))) {
            // Записываем количество символов в таблице кодирования
            dos.writeInt(huffmanCodes.size());
            
            // Записываем таблицу кодирования
            for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
                dos.writeByte(entry.getKey());
                dos.writeUTF(entry.getValue());
            }
            
            // Записываем длину закодированных битов
            dos.writeInt(encodedBits.length());
            
            // Записываем закодированные данные
            int bitLength = encodedBits.length();
            for (int i = 0; i < bitLength; i += 8) {
                String byteStr = encodedBits.substring(i, Math.min(i + 8, bitLength));
                // Дополняем нулями если необходимо
                while (byteStr.length() < 8) {
                    byteStr += "0";
                }
                byte b = (byte) Integer.parseInt(byteStr, 2);
                dos.writeByte(b);
            }
        }
    }
    
    // Класс для хранения закодированных данных
    private static class EncodedData {
        String encodedBits;
        HuffmanNode huffmanTree;
        
        EncodedData(String encodedBits, HuffmanNode huffmanTree) {
            this.encodedBits = encodedBits;
            this.huffmanTree = huffmanTree;
        }
    }
    
    private static EncodedData readEncodedFile(String inputFile) throws IOException {
        try (DataInputStream dis = new DataInputStream(new FileInputStream(inputFile))) {
            // Читаем таблицу кодирования
            int codeTableSize = dis.readInt();
            Map<Byte, String> codes = new HashMap<>();
            
            for (int i = 0; i < codeTableSize; i++) {
                byte character = dis.readByte();
                String code = dis.readUTF();
                codes.put(character, code);
            }
            
            // Восстанавливаем дерево Хаффмана
            HuffmanNode root = rebuildHuffmanTree(codes);
            
            // Читаем длину закодированных битов
            int bitLength = dis.readInt();
            
            // Читаем закодированные данные
            StringBuilder encodedBits = new StringBuilder();
            int bytesToRead = (int) Math.ceil(bitLength / 8.0);
            for (int i = 0; i < bytesToRead; i++) {
                byte b = dis.readByte();
                String byteStr = String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
                encodedBits.append(byteStr);
            }
            
            // Обрезаем до оригинальной длины
            String finalBits = encodedBits.substring(0, bitLength);
            
            return new EncodedData(finalBits, root);
        }
    }
    
    private static HuffmanNode rebuildHuffmanTree(Map<Byte, String> codes) {
        HuffmanNode root = new HuffmanNode(0, null, null);
        
        for (Map.Entry<Byte, String> entry : codes.entrySet()) {
            String code = entry.getValue();
            HuffmanNode current = root;
            
            for (char c : code.toCharArray()) {
                if (c == '0') {
                    if (current.left == null) {
                        current.left = new HuffmanNode(0, null, null);
                    }
                    current = current.left;
                } else {
                    if (current.right == null) {
                        current.right = new HuffmanNode(0, null, null);
                    }
                    current = current.right;
                }
            }
            current.character = entry.getKey();
        }
        
        return root;
    }
    
    private static byte[] decodeData(String encodedBits, HuffmanNode root) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        HuffmanNode current = root;
        
        for (char bit : encodedBits.toCharArray()) {
            if (bit == '0') {
                current = current.left;
            } else {
                current = current.right;
            }
            
            if (current.isLeaf()) {
                bos.write(current.character);
                current = root;
            }
        }
        
        return bos.toByteArray();
    }
    
    // Основной метод
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Huffman Coding Implementation");
            System.out.println("Usage:");
            System.out.println("  Encode: java HuffmanCoding -c inputFile [outputFile]");
            System.out.println("  Decode: java HuffmanCoding -d inputFile [outputFile]");
            System.out.println("\nExamples:");
            System.out.println("  java HuffmanCoding -c input.txt input.huff");
            System.out.println("  java HuffmanCoding -d input.huff output.txt");
            System.out.println("\nDefault extensions:");
            System.out.println("  Encoded files: .huff (binary Huffman format)");
            System.out.println("  Decoded files: .txt for text, .bin for binary");
            return;
        }
        
        try {
            String mode = args[0];
            
            if (mode.equals("-c")) {
                String inputFile = args[1];
                String outputFile;
                if (args.length > 2) {
                    outputFile = args[2];
                } else {
                    // Автоматическое имя с расширением .huff
                    outputFile = inputFile.replaceAll("\\.[^.]*$", "") + ".huff";
                }
                encode(inputFile, outputFile);
                
            } else if (mode.equals("-d")) {
                String inputFile = args[1];
                String outputFile;
                if (args.length > 2) {
                    outputFile = args[2];
                } else {
                    // Автоматическое имя
                    String baseName = inputFile.replaceAll("\\.[^.]*$", "");
                    outputFile = baseName + "_decoded" + (inputFile.toLowerCase().endsWith(".huff") ? ".bin" : ".txt");
                }
                decode(inputFile, outputFile);
                
            } else {
                System.err.println("Unknown mode: " + mode);
                System.out.println("Use -c for encoding or -d for decoding");
            }
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}