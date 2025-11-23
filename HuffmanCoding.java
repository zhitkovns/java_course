import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Класс, представляющий узел в дереве Хаффмана.
 * Используется для построения оптимального префиксного кода.
 */
class HuffmanNode implements Comparable<HuffmanNode> {
    /** Исходный символ (байт). Для внутренних узлов значение -1 */
    byte character;
    
    /** Частота встречаемости символа в исходных данных */
    int frequency;
    
    /** Левый потомок узла (соответствует биту 0) */
    HuffmanNode left;
    
    /** Правый потомок узла (соответствует биту 1) */
    HuffmanNode right;
    
    /**
     * Конструктор для листового узла (символ с частотой)
     * 
     * @param character исходный символ
     * @param frequency частота встречаемости символа
     */
    HuffmanNode(byte character, int frequency) {
        this.character = character;
        this.frequency = frequency;
        this.left = null;
        this.right = null;
    }
    
    /**
     * Конструктор для внутреннего узла (объединяет два поддерева)
     * 
     * @param frequency суммарная частота потомков
     * @param left левый потомок
     * @param right правый потомок
     */
    HuffmanNode(int frequency, HuffmanNode left, HuffmanNode right) {
        this.character = -1;
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }
    
    /**
     * Проверяет, является ли узел листом (не имеет потомков)
     * 
     * @return true если узел листовой, иначе false
     */
    boolean isLeaf() {
        return left == null && right == null;
    }
    
    /**
     * Сравнение узлов по частоте для приоритетной очереди
     * 
     * @param other другой узел для сравнения
     * @return результат сравнения частот
     */
    @Override
    public int compareTo(HuffmanNode other) {
        return Integer.compare(this.frequency, other.frequency);
    }
}

/**
 * Главный класс, реализующий алгоритм кодирования и декодирования Хаффмана.
 * Поддерживает работу с файлами через командную строку.
 */
public class HuffmanCoding {
    
    /**
     * Кодирует исходный файл используя алгоритм Хаффмана.
     * Создает сжатый файл с таблицей кодирования.
     * 
     * @param inputFile путь к исходному файлу
     * @param outputFile путь для сохранения закодированного файла
     * @throws IOException если произошла ошибка ввода-вывода
     */
    public static void encode(String inputFile, String outputFile) throws IOException {
        // Чтение всего файла сразу
        byte[] fileData = readEntireFile(inputFile);
        
        // Получаем оригинальное расширение для восстановления при декодировании
        String originalExtension = getFileExtension(inputFile);
        
        // Подсчет частот символов в исходных данных
        Map<Byte, Integer> frequencyMap = calculateFrequencies(fileData);
        
        // Построение дерева Хаффмана на основе частот
        HuffmanNode root = buildHuffmanTree(frequencyMap);
        
        // Генерация кодов Хаффмана для каждого символа
        Map<Byte, String> huffmanCodes = generateCodes(root);
        
        // Кодирование исходных данных используя таблицу кодов
        String encodedBits = encodeData(fileData, huffmanCodes);
        
        // Проверяем целостность кодирования/декодирования
        validateEncoding(fileData, encodedBits, root);
        
        // Сохранение закодированных данных и таблицы кодирования в компактном формате
        saveEncodedData(encodedBits, huffmanCodes, originalExtension, outputFile, fileData.length);
        
        // Вывод статистики кодирования
        printEncodingStats(inputFile, outputFile, fileData.length, encodedBits.length(), huffmanCodes);
    }
    
    /**
     * Декодирует файл, закодированный алгоритмом Хаффмана.
     * Восстанавливает исходные данные используя таблицу кодирования.
     * 
     * @param inputFile путь к закодированному файлу
     * @param outputFile путь для сохранения раскодированного файла
     * @throws IOException если произошла ошибка ввода-вывода
     */
    public static void decode(String inputFile, String outputFile) throws IOException {
        // Чтение закодированного файла и извлечение таблицы кодирования
        EncodedData encodedData = readEncodedFile(inputFile);
        
        // Декодирование данных используя дерево Хаффмана
        byte[] decodedData = decodeData(encodedData.encodedBits, encodedData.huffmanTree, encodedData.originalSize);
        
        // Сохранение раскодированных данных
        writeEntireFile(outputFile, decodedData);
        
        // Вывод статистики декодирования
        System.out.println("Decoding completed:");
        System.out.println("  Input: " + inputFile);
        System.out.println("  Output: " + outputFile);
        System.out.println("  Decoded size: " + decodedData.length + " bytes");
    }
    
    // Вспомогательные методы:
    
    /**
     * Извлекает расширение файла из имени файла.
     * 
     * @param filename имя файла
     * @return расширение файла (включая точку) или пустую строку если расширения нет
     */
    private static String getFileExtension(String filename) {
        int lastDotIndex = filename.lastIndexOf('.');
        if (lastDotIndex > 0 && lastDotIndex < filename.length() - 1) {
            return filename.substring(lastDotIndex);
        }
        return ""; // Без расширения
    }
    
    /**
     * Читает весь файл в массив байтов.
     * 
     * @param filename путь к файлу
     * @return массив байтов содержимого файла
     * @throws IOException если файл не существует или недоступен для чтения
     */
    private static byte[] readEntireFile(String filename) throws IOException {
        File file = new File(filename);
        byte[] data = new byte[(int)file.length()];
        try (FileInputStream fis = new FileInputStream(file)) {
            fis.read(data);
        }
        return data;
    }
    
    /**
     * Записывает массив байтов в файл.
     * 
     * @param filename путь к файлу для записи
     * @param data данные для записи
     * @throws IOException если файл недоступен для записи
     */
    private static void writeEntireFile(String filename, byte[] data) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(filename)) {
            fos.write(data);
        }
    }
    
    /**
     * Подсчитывает частоту встречаемости каждого байта в данных.
     * 
     * @param data входные данные
     * @return отображение байт -> частота встречаемости
     */
    private static Map<Byte, Integer> calculateFrequencies(byte[] data) {
        Map<Byte, Integer> frequencyMap = new HashMap<>();
        for (byte b : data) {
            frequencyMap.put(b, frequencyMap.getOrDefault(b, 0) + 1);
        }
        return frequencyMap;
    }
    
    /**
     * Строит дерево Хаффмана на основе частот символов.
     * Использует приоритетную очередь для построения оптимального дерева.
     * 
     * @param frequencyMap отображение символов в их частоты
     * @return корень построенного дерева Хаффмана
     */
    private static HuffmanNode buildHuffmanTree(Map<Byte, Integer> frequencyMap) {
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>();
        
        // Создаем листья для каждого символа
        for (Map.Entry<Byte, Integer> entry : frequencyMap.entrySet()) {
            pq.offer(new HuffmanNode(entry.getKey(), entry.getValue()));
        }
        
        // Строим дерево объединяя узлы с наименьшими частотами
        while (pq.size() > 1) {
            HuffmanNode left = pq.poll();
            HuffmanNode right = pq.poll();
            HuffmanNode parent = new HuffmanNode(left.frequency + right.frequency, left, right);
            pq.offer(parent);
        }
        
        return pq.poll(); // Корень дерева
    }
    
    /**
     * Генерирует коды Хаффмана для каждого символа обходя дерево.
     * Гарантирует префиксность кодов.
     */
    private static Map<Byte, String> generateCodes(HuffmanNode root) {
        Map<Byte, String> codes = new HashMap<>();
        
        if (root.isLeaf()) {
            // Особый случай: только один символ
            codes.put(root.character, "0");
            return codes;
        }
        
        generateCodesRecursive(root, "", codes);
        
        return codes;
    }
    
    /**
     * Рекурсивно обходит дерево Хаффмана для генерации кодов.
     * 
     * @param node текущий узел дерева
     * @param code текущий накопленный код
     * @param codes отображение для сохранения кодов
     */
    private static void generateCodesRecursive(HuffmanNode node, String code, 
                                            Map<Byte, String> codes) {
        if (node == null) return;
        
        if (node.isLeaf()) {
            codes.put(node.character, code);
        } else {
            // Рекурсивно обходим левое и правое поддерево
            generateCodesRecursive(node.left, code + "0", codes);
            generateCodesRecursive(node.right, code + "1", codes);
        }
    }
    
    /**
     * Кодирует исходные данные используя таблицу кодов Хаффмана.
     * 
     * @param data исходные данные
     * @param huffmanCodes таблица кодирования
     * @return строка битов ('0' и '1') представляющая закодированные данные
     */
    private static String encodeData(byte[] data, Map<Byte, String> huffmanCodes) {
        StringBuilder encodedBits = new StringBuilder();
        for (byte b : data) {
            encodedBits.append(huffmanCodes.get(b));
        }
        return encodedBits.toString();
    }
    
    /**
     * Проверяет целостность кодирования путем декодирования и сравнения с оригиналом.
     * 
     * @param originalData оригинальные данные
     * @param encodedBits закодированные данные
     * @param root корень дерева Хаффмана
     */
    private static void validateEncoding(byte[] originalData, String encodedBits, HuffmanNode root) {
        try {
            byte[] testDecoded = decodeData(encodedBits, root, originalData.length);
            if (!Arrays.equals(originalData, testDecoded)) {
                System.err.println("WARNING: Encoding/decoding test failed! Files may be corrupted.");
            }
        } catch (Exception e) {
            System.err.println("WARNING: Encoding test failed: " + e.getMessage());
        }
    }
    
    /**
     * Сохраняет закодированные данные в бинарный файл в компактном формате.
     * Использует оптимизированное хранение для малых файлов и таблиц кодов.
     * 
     * @param encodedBits закодированные данные в виде строки битов
     * @param huffmanCodes таблица кодирования
     * @param originalExtension оригинальное расширение файла
     * @param outputFile путь для сохранения
     * @param originalSize оригинальный размер файла в байтах
     * @throws IOException если произошла ошибка записи
     */
    private static void saveEncodedData(String encodedBits, Map<Byte, String> huffmanCodes, 
                                    String originalExtension, String outputFile, int originalSize) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(outputFile))) {
            
            int tableSize = huffmanCodes.size();
            int bitLength = encodedBits.length();
            
            // Флаги: бит 0 - есть расширение, бит 1 - специальный формат для 1 символа, бит 2 - большая таблица (>255)
            byte flags = 0;
            boolean hasExtension = !originalExtension.isEmpty();
            boolean isSingleChar = tableSize == 1;
            boolean isLargeTable = tableSize > 255;
            
            if (hasExtension) flags |= 0x01;
            if (isSingleChar) flags |= 0x02;
            if (isLargeTable) flags |= 0x04;
            dos.writeByte(flags);
            
            // Записываем расширение если есть (без точки)
            if (hasExtension) {
                String ext = originalExtension.substring(1);
                dos.writeByte(ext.length()); // Длина расширения (1 байт)
                dos.writeBytes(ext);         // Само расширение
            }
            
            // Специальный компактный формат для одного символа
            if (isSingleChar) {
                byte singleChar = huffmanCodes.keySet().iterator().next();
                dos.writeByte(singleChar);  // Сам символ (1 байт)
                dos.writeShort(originalSize); // Количество повторений (2 байта)
            } else {
                // Записываем размер таблицы
                if (isLargeTable) {
                    dos.writeShort(tableSize); // Для больших таблиц используем short (2 байта)
                } else {
                    dos.writeByte(tableSize);  // Для маленьких таблиц используем byte (1 байт)
                }
                
                // Сохраняем таблицу кодов в компактном бинарном формате
                for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
                    dos.writeByte(entry.getKey()); // Символ (1 байт)
                    String code = entry.getValue();
                    int codeLength = code.length();
                    
                    // Записываем длину кода
                    if (isLargeTable) {
                        dos.writeShort(codeLength); // Для больших таблиц используем short
                    } else {
                        dos.writeByte(codeLength);  // Для маленьких таблиц используем byte
                    }
                    
                    // Сохраняем код как последовательность байтов
                    int bytesNeeded = (codeLength + 7) / 8;
                    for (int i = 0; i < bytesNeeded; i++) {
                        int startBit = i * 8;
                        int endBit = Math.min(startBit + 8, codeLength);
                        String byteStr = code.substring(startBit, endBit);
                        
                        // Дополняем нулями если нужно
                        if (byteStr.length() < 8) {
                            byteStr = String.format("%-8s", byteStr).replace(' ', '0');
                        }
                        
                        byte b = (byte) Integer.parseInt(byteStr, 2);
                        dos.writeByte(b);
                    }
                }
                
                // Сохраняем длину закодированных данных в битах
                if (bitLength < 256) {
                    dos.writeByte(bitLength); // Короткая длина (1 байт)
                } else if (bitLength < 65536) {
                    dos.writeByte(255);       // Маркер для средней длины
                    dos.writeShort(bitLength); // Средняя длина (2 байта)
                } else {
                    dos.writeByte(254);       // Маркер для длинной длины  
                    dos.writeInt(bitLength);  // Длинная длина (4 байта)
                }
                
                // Сохраняем закодированные данные
                int bytesToWrite = (int) Math.ceil(bitLength / 8.0);
                for (int i = 0; i < bytesToWrite; i++) {
                    int startBit = i * 8;
                    int endBit = Math.min(startBit + 8, bitLength);
                    String byteStr = encodedBits.substring(startBit, endBit);
                    
                    // Дополняем последний байт нулями если нужно
                    if (byteStr.length() < 8) {
                        byteStr = String.format("%-8s", byteStr).replace(' ', '0');
                    }
                    
                    byte b = (byte) Integer.parseInt(byteStr, 2);
                    dos.writeByte(b);
                }
            }
        }
    }
    
    /**
     * Внутренний класс для хранения данных, извлеченных из закодированного файла.
     * Используется для передачи данных между методами чтения и декодирования.
     */
    private static class EncodedData {
        /** Закодированные данные в виде строки битов */
        String encodedBits;
        
        /** Дерево Хаффмана для декодирования */
        HuffmanNode huffmanTree;
        
        /** Оригинальное расширение файла */
        String originalExtension;
        
        /** Оригинальный размер файла (для специальных форматов) */
        int originalSize;
        
        /**
         * Конструктор для данных закодированного файла.
         * 
         * @param encodedBits закодированные данные
         * @param huffmanTree дерево Хаффмана
         * @param originalExtension оригинальное расширение
         * @param originalSize оригинальный размер файла
         */
        EncodedData(String encodedBits, HuffmanNode huffmanTree, String originalExtension, int originalSize) {
            this.encodedBits = encodedBits;
            this.huffmanTree = huffmanTree;
            this.originalExtension = originalExtension;
            this.originalSize = originalSize;
        }
    }
    
    /**
     * Читает закодированный файл и извлекает таблицу кодирования и данные.
     * Поддерживает как стандартный, так и компактный форматы.
     * 
     * @param inputFile путь к закодированному файлу
     * @return объект с извлеченными данными
     * @throws IOException если файл поврежден или недоступен
     */
    private static EncodedData readEncodedFile(String inputFile) throws IOException {
        try (DataInputStream dis = new DataInputStream(new FileInputStream(inputFile))) {
            
            byte flags = dis.readByte();
            boolean hasExtension = (flags & 0x01) != 0;
            boolean isSingleChar = (flags & 0x02) != 0;
            boolean isLargeTable = (flags & 0x04) != 0;
            
            // Читаем расширение если есть
            String originalExtension = "";
            if (hasExtension) {
                int extLen = dis.readByte() & 0xFF;
                byte[] extBytes = new byte[extLen];
                dis.readFully(extBytes);
                originalExtension = "." + new String(extBytes);
            }
            
            Map<Byte, String> codes = new HashMap<>();
            int originalSize = -1;
            String encodedBits = "";
            
            if (isSingleChar) {
                // Компактный формат для одного символа
                byte singleChar = dis.readByte();
                originalSize = dis.readShort() & 0xFFFF;
                codes.put(singleChar, "0");
                // Генерируем последовательность нулей соответствующей длины
                encodedBits = "0".repeat(originalSize);
            } else {
                // Читаем размер таблицы
                int tableSize;
                if (isLargeTable) {
                    tableSize = dis.readShort() & 0xFFFF;
                } else {
                    tableSize = dis.readByte() & 0xFF;
                }
                
                // Читаем таблицу кодов
                for (int i = 0; i < tableSize; i++) {
                    byte character = dis.readByte();
                    int codeLength;
                    if (isLargeTable) {
                        codeLength = dis.readShort() & 0xFFFF;
                    } else {
                        codeLength = dis.readByte() & 0xFF;
                    }
                    int bytesNeeded = (codeLength + 7) / 8;
                    
                    // Читаем код как последовательность байтов
                    StringBuilder codeBuilder = new StringBuilder();
                    int bitsRead = 0;
                    for (int j = 0; j < bytesNeeded; j++) {
                        byte b = dis.readByte();
                        String byteStr = String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
                        
                        // Берем только нужное количество битов
                        int bitsToTake = Math.min(8, codeLength - bitsRead);
                        codeBuilder.append(byteStr.substring(0, bitsToTake));
                        bitsRead += bitsToTake;
                    }
                    
                    String code = codeBuilder.toString();
                    codes.put(character, code);
                }
                
                // Читаем длину закодированных данных
                int bitLength;
                byte lengthFlag = dis.readByte();
                if (lengthFlag == (byte)255) {
                    bitLength = dis.readShort() & 0xFFFF;
                } else if (lengthFlag == (byte)254) {
                    bitLength = dis.readInt();
                } else {
                    bitLength = lengthFlag & 0xFF;
                }
                
                // Читаем закодированные данные
                int bytesToRead = (int) Math.ceil(bitLength / 8.0);
                StringBuilder encodedBitsBuilder = new StringBuilder();
                for (int i = 0; i < bytesToRead; i++) {
                    byte b = dis.readByte();
                    String byteStr = String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
                    encodedBitsBuilder.append(byteStr);
                }
                encodedBits = encodedBitsBuilder.substring(0, bitLength);
            }
            
            // Восстанавливаем дерево Хаффмана из таблицы кодов
            HuffmanNode root = rebuildHuffmanTree(codes);
            
            return new EncodedData(encodedBits, root, originalExtension, originalSize);
        }
    }

    /**
     * Восстанавливает дерево Хаффмана из таблицы кодов.
     * 
     * @param codes таблица кодирования (символ -> код)
     * @return корень восстановленного дерева Хаффмана
     */
    private static HuffmanNode rebuildHuffmanTree(Map<Byte, String> codes) {
        HuffmanNode root = new HuffmanNode((byte)-1, 0);
        
        for (Map.Entry<Byte, String> entry : codes.entrySet()) {
            byte character = entry.getKey();
            String code = entry.getValue();
            HuffmanNode current = root;
            
            for (int i = 0; i < code.length(); i++) {
                char c = code.charAt(i);
                
                if (c == '0') {
                    if (current.left == null) {
                        current.left = new HuffmanNode((byte)-1, 0);
                    }
                    current = current.left;
                } else {
                    if (current.right == null) {
                        current.right = new HuffmanNode((byte)-1, 0);
                    }
                    current = current.right;
                }
            }
            
            // Убеждаемся, что узел является листом
            current.character = character;
        }
        
        return root;
    }
    
    /**
     * Декодирует данные используя дерево Хаффмана.
     * 
     * @param encodedBits закодированные данные в виде строки битов
     * @param root корень дерева Хаффмана
     * @param originalSize оригинальный размер файла (для специальных случаев)
     * @return раскодированные данные
     * @throws IOException если произошла ошибка ввода-вывода
     */
    private static byte[] decodeData(String encodedBits, HuffmanNode root, int originalSize) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        HuffmanNode current = root;
        
        // Особый случай: дерево состоит только из одного символа
        if (root.isLeaf()) {
            int count = originalSize > 0 ? originalSize : encodedBits.length();
            for (int i = 0; i < count; i++) {
                bos.write(root.character);
            }
            return bos.toByteArray();
        }
        
        // Стандартное декодирование
        int decodedBytes = 0;
        for (int i = 0; i < encodedBits.length(); i++) {
            char bit = encodedBits.charAt(i);
            
            if (bit == '0') {
                if (current.left == null) {
                    throw new IOException("Invalid Huffman tree: left child is null at position " + i);
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    throw new IOException("Invalid Huffman tree: right child is null at position " + i);
                }
                current = current.right;
            }
            
            if (current.isLeaf()) {
                bos.write(current.character);
                decodedBytes++;
                current = root;
            }
        }
        
        // Проверяем, что закончили в корневом узле
        if (current != root) {
            throw new IOException("Invalid encoded data: unfinished sequence at the end");
        }
        
        return bos.toByteArray();
    }
    
    /**
     * Выводит статистику кодирования.
     * 
     * @param inputFile исходный файл
     * @param outputFile закодированный файл
     * @param originalSize оригинальный размер
     * @param encodedBitsLength длина закодированных данных в битах
     * @param huffmanCodes таблица кодов Хаффмана
     */
    private static void printEncodingStats(String inputFile, String outputFile, 
                                         int originalSize, int encodedBitsLength, 
                                         Map<Byte, String> huffmanCodes) {
        int compressedSize = (int)Math.ceil(encodedBitsLength / 8.0);
        
        System.out.println("Encoding completed:");
        System.out.println("  Input: " + inputFile);
        System.out.println("  Output: " + outputFile);
        System.out.println("  Original size: " + originalSize + " bytes");
        System.out.println("  Compressed size: " + compressedSize + " bytes");
        System.out.println("  Compression ratio: " + String.format("%.2f", 
            (1 - (double)compressedSize / originalSize) * 100) + "%");
        
        // Выводим таблицу кодов только для небольших алфавитов
        if (huffmanCodes.size() <= 10) {
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
        } else {
            System.out.println("\nHuffman codes: " + huffmanCodes.size() + " codes generated");
        }
    }
    
    /**
     * Главный метод программы. Обрабатывает аргументы командной строки
     * и запускает соответствующие операции кодирования или декодирования.
     * 
     * @param args аргументы командной строки:
     *             -c inputFile [outputFile] для кодирования
     *             -d inputFile [outputFile] для декодирования
     */
    public static void main(String[] args) {
        if (args.length < 2) {
            printUsage();
            return;
        }
        
        try {
            String mode = args[0];
            
            if (mode.equals("-c")) {
                handleEncoding(args);
            } else if (mode.equals("-d")) {
                handleDecoding(args);
            } else {
                System.err.println("Unknown mode: " + mode);
                printUsage();
            }
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Обрабатывает операцию кодирования.
     * 
     * @param args аргументы командной строки
     * @throws IOException если произошла ошибка ввода-вывода
     */
    private static void handleEncoding(String[] args) throws IOException {
        String inputFile = args[1];
        String outputFile;
        if (args.length > 2) {
            outputFile = args[2];
        } else {
            // Автоматическое имя с расширением .huff
            outputFile = inputFile.replaceAll("\\.[^.]*$", "") + ".huff";
        }
        encode(inputFile, outputFile);
    }
    
    /**
     * Обрабатывает операцию декодирования.
     * 
     * @param args аргументы командной строки
     * @throws IOException если произошла ошибка ввода-вывода
     */
    private static void handleDecoding(String[] args) throws IOException {
        String inputFile = args[1];
        String outputFile;
        if (args.length > 2) {
            outputFile = args[2];
        } else {
            // Автоматическое имя с оригинальным расширением
            EncodedData data = readEncodedFile(inputFile);
            String baseName = inputFile.replaceAll("\\.huff$", "");
            outputFile = baseName + "_decoded" + data.originalExtension;
        }
        decode(inputFile, outputFile);
    }
    
    /**
     * Выводит справку по использованию программы.
     */
    private static void printUsage() {
        System.out.println("Huffman Coding Implementation");
        System.out.println("Usage:");
        System.out.println("  Encode: java HuffmanCoding -c inputFile [outputFile]");
        System.out.println("  Decode: java HuffmanCoding -d inputFile [outputFile]");
        System.out.println("\nExamples:");
        System.out.println("  java HuffmanCoding -c input.txt input.huff");
        System.out.println("  java HuffmanCoding -d input.huff output.txt");
        System.out.println("\nAutomatic file naming:");
        System.out.println("  Encoded files: original_name.huff");
        System.out.println("  Decoded files: original_name_decoded.original_extension");
    }
}