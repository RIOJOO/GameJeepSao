import java.util.ArrayList;
import java.util.List;

public class MeanStory {
    public static List<Dialogue> getStory() {
        List<Dialogue> list = new ArrayList<>();

        // --- ฉากเกริ่นนำ: ห้องสมุด (รูปจะถูก Fix เป็นฉากหลังเต็มจอ) ---
        list.add(new Dialogue("บรรยาย", "วันแรก ๆ ของการเปิดเทอม เราแวะเข้าห้องสมุดเพื่อหาที่เงียบ ๆ", "res/BGLibrary.jpg")); // [cite: 2, 3, 11]
        
        // --- ฉากพบกันครั้งแรก (เปลี่ยนฉากหลังเป็นรูปตอนหยิบหนังสือ) ---
        list.add(new Dialogue("บรรยาย", "ในห้องสมุดเงียบๆ มีผู้หญิงคนหนึ่งกำลังเอื้อมหยิบหนังสือไม่ถึง", "res/unnamed.jpg")); // [cite: 4, 5, 12]
         list.add(new Dialogue("บรรยาย", "เธอดูตั้งใจมาก จนเผลอยิ้มออกมา", "res/unnamed.jpg")); //มาพิมพ์วงเล็บเอง
        // ตัวเลือกแรก: เริ่มต้นความสัมพันธ์ (ใช้ชื่อ "คุณ" เพื่อให้ฉากหลังเดิมยังคงอยู่)
        list.add(new Dialogue("คุณ", "“เข้าไปช่วยดีไหมนะ...”", "res/unnamed.jpg",
            new String[]{"เข้าไปช่วยหยิบให้","ยิ้มให้แล้วเดินผ่าน","ยืนดูห่างๆ"}, 
            new int[]{3, 15 , 16} // [cite: 13, 40]
        ));

    
        
        // เปลี่ยนฉากหลังกลับเป็นห้องสมุดปกติ [cite: 8-12]
         list.add(new Dialogue("คุณ", "“ นี่ครับหนังสือที่คุณต้องการ ”", "res/BGLibrary.jpg|res/Givebook.png")); // [cite: 6, 7]
        list.add(new Dialogue("มีน", "“ขอบคุณนะคะ”", "res/BGLibrary.jpg|res/Mean2.png")); // [cite: 14, 19]
        list.add(new Dialogue("บรรยาย", " นั่นคือจุดเริ่มต้นของความสัมพันธ์เรา ", "res/BGLibrary.jpg"));

        list.add(new Dialogue("มีน", "“ชอบอ่านหนังสือด้วยกันแบบนี้ มันเงียบแต่ไม่เหงาแฮะ”", "res/BGLibrary.jpg|res/Mean2.png")); // [cite: 21, 22]
        
        // --- รูทสนิท (Index 3) ---
        list.add(new Dialogue("คุณ", "“ นี่ครับหนังสือที่คุณต้องการ ”", "res/BGLibrary.jpg|res/Givebook.png")); // [cite: 6, 7]
        list.add(new Dialogue("มีน", "“ ขอบคุณมากเลยค่ะ ถ้าไม่ได้พี่ช่วยคงลำบากแน่เลย ”", "res/BGLibrary.jpg|res/Mean2.png")); // [cite: 6, 7]
        
        // ตัวเลือกตัดสินใจตอนจบ (Index 8)
        list.add(new Dialogue("มีน", "“บางที...ก็อยากให้มานั่งข้าง ๆ ทุกวัน”", "res/BGLibrary.jpg|res/Mean2.png",
            new String[]{"ฉันก็อยากนั่งข้างเธอตลอดไป", "มาอ่านด้วยกันบ่อยๆ นะในฐานะเพื่อน"},
            new int[]{9, 12} // [cite: 26, 34]
        ));

        // --- Good End (Index 9) ---
        list.add(new Dialogue("มีน", "“เล่มนี้สนุกมาก แต่อยากอ่านไปพร้อมกันมากกว่า”", "res/BGLibrary.jpg|res/Mean2.png")); // [cite: 29, 30]
        list.add(new Dialogue("บรรยาย", "มีนยื่นหนังสือที่มีลายมือเล็กๆ เขียนคั่นไว้ แล้วเธอก็นั่งข้างเราเหมือนทุกวัน", "res/BGLibrary.jpg|res/Mean2.png")); // [cite: 27, 28, 31, 32]
        list.add(new Dialogue("SYSTEM", "--- HAPPY ENDING ---", null));

        // --- Friend End (Index 12) ---
        list.add(new Dialogue("มีน", "“ขอบคุณนะที่ชอบมาอ่านด้วยกัน อยู่ด้วยแล้วสบายใจดี”", "res/BGLibrary.jpg|res/Mean2.png")); // [cite: 35-37]
        list.add(new Dialogue("บรรยาย", "ความสัมพันธ์ของเรา กลายเป็นมุมสงบ ๆ ในห้องสมุดเสมอ", "res/BGLibrary.jpg")); // [cite: 38, 39]
        list.add(new Dialogue("SYSTEM", "--- FRIEND ENDING ---", null));

        // --- Bad End (Index 15) ---
        // เปลี่ยนฉากหลังเป็นรูปตอนจบแบบเศร้าตามที่ต้องการ [cite: 40-45]
        list.add(new Dialogue("บรรยาย", "เธอก้มหน้าอ่านหนังสือเงียบ ๆ เหมือนกำลังรอใครบางคนที่ไม่ได้กลับมาอีก", "res/empty_chair.jpg")); 
        list.add(new Dialogue("บรรยาย", "มุมประจำยังเหมือนเดิม แต่ที่นั่งข้างมีนว่างเปล่าเสมอ...", "res/empty_chair.jpg")); // [cite: 41, 42]
        list.add(new Dialogue("SYSTEM", "--- BAD ENDING ---", null));

        return list;
    }
}