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
        
        // Сохранение закодированных данных и таблицы кодирования
        saveEncodedData(encodedBits, huffmanCodes, originalExtension, outputFile);
        
        // Вывод статистики кодирования
        System.out.println("Encoding completed:");
        System.out.println("  Input: " + inputFile);
        System.out.println("  Output: " + outputFile);
        System.out.println("  Original size: " + fileData.length + " bytes");
        System.out.println("  Compressed size: " + (int)Math.ceil(encodedBits.length() / 8.0) + " bytes");
        System.out.println("  Compression ratio: " + String.format("%.2f", 
            (1 - (Math.ceil(encodedBits.length() / 8.0) / (double)fileData.length)) * 100) + "%");
        
        // Вывод таблицы кодирования для отладки
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
        byte[] decodedData = decodeData(encodedData.encodedBits, encodedData.huffmanTree);
        
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
     * 
     * @param root корень дерева Хаффмана
     * @return отображение символов в их коды Хаффмана
     */
    private static Map<Byte, String> generateCodes(HuffmanNode root) {
        Map<Byte, String> codes = new HashMap<>();
        generateCodesRecursive(root, "", codes);
        return codes;
    }
    
    /**
     * Рекурсивно обходит дерево Хаффмана для генерации кодов.
     * 
     * @param node текущий узел дерева
     * @param code текущий накопленный код
     * @codes отображение для сохранения кодов
     */
    private static void generateCodesRecursive(HuffmanNode node, String code, Map<Byte, String> codes) {
        if (node == null) return;
        
        if (node.isLeaf()) {
            // Для листа сохраняем код (особый случай - один символ)
            codes.put(node.character, code.isEmpty() ? "0" : code);
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
     * Сохраняет закодированные данные в бинарный файл.
     * Формат файла: расширение + таблица кодов + длина данных + закодированные данные
     * 
     * @param encodedBits закодированные данные в виде строки битов
     * @param huffmanCodes таблица кодирования
     * @param originalExtension оригинальное расширение файла
     * @param outputFile путь для сохранения
     * @throws IOException если произошла ошибка записи
     */
    private static void saveEncodedData(String encodedBits, Map<Byte, String> huffmanCodes, 
                                      String originalExtension, String outputFile) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(outputFile))) {
            // Записываем оригинальное расширение для восстановления при декодировании
            dos.writeUTF(originalExtension);
            
            // Записываем количество символов в таблице кодирования
            dos.writeInt(huffmanCodes.size());
            
            // Записываем таблицу кодирования (символ -> код)
            for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
                dos.writeByte(entry.getKey());
                dos.writeUTF(entry.getValue());
            }
            
            // Записываем длину закодированных данных в битах
            dos.writeInt(encodedBits.length());
            
            // Записываем закодированные данные, упаковывая биты в байты
            int bitLength = encodedBits.length();
            for (int i = 0; i < bitLength; i += 8) {
                String byteStr = encodedBits.substring(i, Math.min(i + 8, bitLength));
                // Дополняем нулями если необходимо для выравнивания до байта
                while (byteStr.length() < 8) {
                    byteStr += "0";
                }
                byte b = (byte) Integer.parseInt(byteStr, 2);
                dos.writeByte(b);
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
        
        /**
         * Конструктор для данных закодированного файла.
         * 
         * @param encodedBits закодированные данные
         * @param huffmanTree дерево Хаффмана
         * @param originalExtension оригинальное расширение
         */
        EncodedData(String encodedBits, HuffmanNode huffmanTree, String originalExtension) {
            this.encodedBits = encodedBits;
            this.huffmanTree = huffmanTree;
            this.originalExtension = originalExtension;
        }
    }
    
    /**
     * Читает закодированный файл и извлекает таблицу кодирования и данные.
     * 
     * @param inputFile путь к закодированному файлу
     * @return объект с извлеченными данными
     * @throws IOException если файл поврежден или недоступен
     */
    private static EncodedData readEncodedFile(String inputFile) throws IOException {
        try (DataInputStream dis = new DataInputStream(new FileInputStream(inputFile))) {
            // Читаем оригинальное расширение файла
            String originalExtension = dis.readUTF();
            
            // Читаем таблицу кодирования
            int codeTableSize = dis.readInt();
            Map<Byte, String> codes = new HashMap<>();
            
            for (int i = 0; i < codeTableSize; i++) {
                byte character = dis.readByte();
                String code = dis.readUTF();
                codes.put(character, code);
            }
            
            // Восстанавливаем дерево Хаффмана из таблицы кодов
            HuffmanNode root = rebuildHuffmanTree(codes);
            
            // Читаем длину закодированных данных в битах
            int bitLength = dis.readInt();
            
            // Читаем закодированные данные
            StringBuilder encodedBits = new StringBuilder();
            int bytesToRead = (int) Math.ceil(bitLength / 8.0);
            for (int i = 0; i < bytesToRead; i++) {
                byte b = dis.readByte();
                // Конвертируем байт в строку битов
                String byteStr = String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
                encodedBits.append(byteStr);
            }
            
            // Обрезаем до оригинальной длины (убираем padding)
            String finalBits = encodedBits.substring(0, bitLength);
            
            return new EncodedData(finalBits, root, originalExtension);
        }
    }
    
    /**
     * Восстанавливает дерево Хаффмана из таблицы кодов.
     * 
     * @param codes таблица кодирования (символ -> код)
     * @return корень восстановленного дерева Хаффмана
     */
    private static HuffmanNode rebuildHuffmanTree(Map<Byte, String> codes) {
        HuffmanNode root = new HuffmanNode(0, null, null);
        
        // Для каждого символа и его кода строим путь в дереве
        for (Map.Entry<Byte, String> entry : codes.entrySet()) {
            String code = entry.getValue();
            HuffmanNode current = root;
            
            // Проходим по каждому биту кода, строя дерево
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
            // В конце пути сохраняем символ
            current.character = entry.getKey();
        }
        
        return root;
    }
    
    /**
     * Декодирует данные используя дерево Хаффмана.
     * 
     * @param encodedBits закодированные данные в виде строки битов
     * @param root корень дерева Хаффмана
     * @return раскодированные данные
     * @throws IOException если произошла ошибка ввода-вывода
     */
    private static byte[] decodeData(String encodedBits, HuffmanNode root) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        HuffmanNode current = root;
        
        // Проходим по каждому биту и движемся по дереву
        for (char bit : encodedBits.toCharArray()) {
            if (bit == '0') {
                current = current.left;
            } else {
                current = current.right;
            }
            
            // При достижении листа сохраняем символ и возвращаемся к корню
            if (current.isLeaf()) {
                bos.write(current.character);
                current = root;
            }
        }
        
        return bos.toByteArray();
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