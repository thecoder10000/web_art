package dduwcom.mobile.finalreport;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class BookDBHelper extends SQLiteOpenHelper {

    final static String TAG = "BookDBHelper";
    final static String DB_NAME = "books.db";
    public final static String TABLE_NAME = "book_table";
    public final static String COL_ID = "_id";
    public final static String COL_TITLE = "title";
    public final static String COL_AUTHOR = "author";
    public final static String COL_PUBLISHER = "publisher";
    public final static String COL_SUMMARY = "summary";
    public final static String COL_PRICE = "price";
    public final static String COL_IMAGE = "image";

    String summary_0 = null;
    String summary_1 = null;
    String summary_2 = null;
    String summary_3 = null;
    String summary_4 = null;
    String summary_5 = null;
    String summary_6 = null;

    public BookDBHelper(Context context) {
        super(context, DB_NAME, null, 16); //8은 글자만 15는 그림도
        summary_0 = "  『동물농장』과 함께 조지 오웰을 대표하는 작품으로, " +
                "전제주의라는 거대한 지배 시스템 앞에 놓인 한 개인이 어떻게 저항하다가 어떻게 파멸해 가는지, " +
                "그 과정과 양상, 그리고 배후를 적나라하게 보여주는 디스토피아 소설이다. \n\n" +
                "  무대인 오세아니아는 전체주의의 극한적인 양상을 띠고 있는 나라이다. " +
                "오세아니아의 정치 통제 기구인 당은 허구적 인물인 빅 브라더를 내세워 " +
                "독재 권력의 극대화를 꾀하는 한편, 정치 체제를 항구적으로 유지하기 위해 텔레스크린, 사상경찰, 마이크로폰, 헬리콥터 등을 이용하여 " +
                "당원들의 사생활을 철저하게 감시한다. 당의 정당성을 획득하는 것과 동시에 당원들의 사상적인 통제를 위해 과거의 사실을 끊임없이 날조하고, " +
                "새로운 언어인 신어를 창조하여 생각과 행동을 속박함은 물론, 인간의 기본적인 욕구인 성욕까지 통제한다.";

        summary_1 = "이성의 광기 속으로 가라앉는 자폐적 청춘의 초상! " +
                "러시아의 대문호 도스토예프스키의 대작 『죄와 벌』 제1권 \n" +
                "도스토예프스키가 8년간의 유형 생활 후 발표한 두 번째 작품으로, 인간의 내면 깊은 곳에 숨겨진 심리를 파헤치고 있다." +
                " 죄와 속죄에 대한 다양한 인식들이 서로 갈등하고 교차한다. \n\n" +
                "1860년대 후반의 페테르부르크. 지방 소도시 출신의 청년 라스콜니코프는 형편이 어려워 학업을 중단하고 ‘관’ 같은 방에 틀어박혀 자신만의 완벽한 계획을 세운다. " +
                "어느 날 저녁, 그는 머릿속으로 구상한 계획에 따라 전당포 노파의 그녀의 이복여동생을 도끼로 살해한다. " +
                "누구의 눈에도 띄지 않은 완전 범죄였지만, 예심판사는 그의 심리를 꿰뚫으며 압박해 온다. " +
                "이성과 관념만이 가득했던 라스콜니코프의 마음에는 조금씩 불안감이 싹트고, 가족들을 먹여 살리기 위해 몸을 팔지만 누구보다 순결한 소냐를 만나면서 " +
                "점점 더 혼란을 느끼는데….";

        summary_2 = "  이성의 광기 속으로 가라앉는 자폐적 청춘의 초상! " +
                "러시아의 대문호 도스토예프스키의 대작 『죄와 벌』 제2권 \n" +
                "  도스토예프스키가 8년간의 유형 생활 후 발표한 두 번째 작품으로, 인간의 내면 깊은 곳에 숨겨진 심리를 파헤치고 있다." +
                " 죄와 속죄에 대한 다양한 인식들이 서로 갈등하고 교차한다. \n\n" +
                "  1860년대 후반의 페테르부르크. 지방 소도시 출신의 청년 라스콜니코프는 형편이 어려워 학업을 중단하고 ‘관’ 같은 방에 틀어박혀 자신만의 완벽한 계획을 세운다. " +
                "어느 날 저녁, 그는 머릿속으로 구상한 계획에 따라 전당포 노파의 그녀의 이복여동생을 도끼로 살해한다. " +
                "누구의 눈에도 띄지 않은 완전 범죄였지만, 예심판사는 그의 심리를 꿰뚫으며 압박해 온다. " +
                "이성과 관념만이 가득했던 라스콜니코프의 마음에는 조금씩 불안감이 싹트고, 가족들을 먹여 살리기 위해 몸을 팔지만 누구보다 순결한 소냐를 만나면서 " +
                "점점 더 혼란을 느끼는데….";

        summary_3 = "  싱클레어는 따뜻하고 밝고 평온한 가정에서 사랑받으며 자랐다. " +
                "그러던 어느 날 크로머를 통해 어둠의 세계에 발을 내딛는다. " +
                "깨끗하고 안전한 세계에 머무는 것을 좋아하면서도 싱클레어는 금지된 어두운 세계에 강하게 이끌린다. " +
                "불안과 악몽에 시달리는 싱클레어를 고통에서 벗어나게 해준 것은 신비로운 전학생 데미안이었다. \n" +
                "  데미안은 싱클레어가 그동안 배운 선과 악을 새로운 시선으로 볼 수 있게 해 주었다. " +
                "싱클레어는 허락된 밝은 시계와 금지된 어두운 세계, 내면의 선악 사이에서 깊이 갈등한다. " +
                "데미안은 싱클레어에게 삶을 있는 그대로 받아들이고 존중하라고 충고하는데…….\n\n" +
                "  데미안과 싱클레어, 두 인물이 나누는 우정을 통해 " +
                "우리는 어떤 삶의 진실을 마주하게 될까?";

        summary_4 = "  이상은 시, 소설, 수필에 걸쳐 두루 작품 활동을 한 일제 식민지시대의 대표적인 작가이다. " +
                "특히 그의 시와 소설은 1930년대 모더니즘의 특성을 첨예하게 드러내준다. \n" +
                "  시의 경우 그가 보여주는 것은 현대인의 황량한 내면 풍경이며, 「오감도 시 제1호」처럼 반리얼리즘 기법을 통한 불안과 공포라는 주제로 요약된다. " +
                "또한 그의 소설은 전통적인 소설 양식의 해체를 통해 현대인의 삶의 조건을 보여주는데, " +
                "「날개」의 경우 그것은 의식의 흐름 기법을 통해 어떤 일상적 현실과도 관계를 맺을 수 없는, 파편화되고 물화된 현대인의 소외로 나타나고 있다.";

        summary_5 = " 운명을 예측할 수 없는 도박 같은 재일교포의 삶. " +
                "  『파친코』는 운명을 알 수 없는 도박이라는 점에서 재일교포들의 삶을 상징하는 좋은 은유라고 할 수 있다. " +
                "파친코 운영은 경제적인 풍요로움을 안겨줄 수는 있으나 야쿠자와의 연관성 때문에 폭력적 이미지가 강하다. " +
                "그럼에도 불구하고 재일교포들은 파친코 사업에 뛰어든다. " +
                "편견으로 점철된 타국에서 『파친코』는 재일교포들에게 돈과 권력과 신분의 상승을 안겨줄 수 있는 유일한 수단이었기 때문이다. \n" +
                "  이렇듯 『파친코』는 단순한 도박 이야기가 아니라, 한국의 근현대사가 얼마나 비극으로 점철되어 있는지를 새삼 깨닫게 해주는 작품이다. " +
                "『파친코』는 “역사가 우리를 망쳐놨지만 그래도 상관없다”라는 말로 시작된다. " +
                "그것은 곧 어려운 시기에 문제가 많은 나라에서 태어났지만 그래도 희망이 있다는 것을 의미하는 것이리라. " +
                "역사가 우리를 망치고, 정치가들이 나라를 망쳐도 국민들은 고난을 극복하고 살아남을 것이라는 것이다. " +
                "그래서 『파친코』의 궁극적인 메시지는 희망과 극복이다. \n\n" +
                "- 김성곤 (조지워싱턴대 석학교수)";

        summary_6 = " 한국계 1.5세인 미국 작가 이민진의 장편소설 『파친코』 제2권. \n" +
                "  내국인이면서 끝내 이방인일 수밖에 없었던 자이니치(재일동포)들의 처절한 생애를 깊이 있는 필체로 담아낸 작품이다. " +
                "삶은 모두에게나 고통이지만 일제강점기에 일본으로 건너간 조선인들에게는 더더욱 가혹했다. " +
                "그들은 그저 자식만큼은 자신들보다 나은 대우를 받으며 살 수 있기를 바라는 보통 사람들이었지만, 시대는 그들의 평범한 소원을 들어줄 만큼 호락호락한 것이 아니었다. \n\n" +
                "   가난한 집의 막내딸 양진은 돈을 받고 언청이에 절름발이인 훈이와 결혼한다. 양진은 남편 훈이와 함께 하숙집을 운영해나가며 불평 한마디 하지 않는다. 그녀는 온갖 궂은일을 다 하면서 유일한 자식이자 비장애인으로 태어난 딸 선자를 묵묵히 키워나간다. "+
                "부모의 살뜰한 보살핌과 사랑을 받고 자란 선자는 안타깝게도 엄마 나이 또래의 생선 중매상 한수에게 빠져 결국에는 한수가 유부남이라는 사실도 모른 채 그의 아이를 임신하고 만다. 불행의 나락에 빠진 선자를 목사 이삭이 아내로 맞이하면서 구원을 받게 되고, 둘은 새로운 인생을 위해 이삭의 형 요셉 부부가 사는 일본의 오사카로 향한다. " +
                "일본에서 한수의 핏줄인 첫째 노아와 이삭의 핏줄인 둘째 모자수를 낳은 선자는 친정엄마인 양진처럼 여자로서의 인생은 잊어버린 채 아내와 어머니로서의 삶을 고생스럽게 살아가는데……. "+
                "부산 영도의 기형아 훈이, 그의 딸 선자, 선자가 일본으로 건너가 낳은 아들 노아와 모자수, 그리고 모자수의 아들인 솔로몬에 이르는 그 치열한 역사, 뼈아픈 시대적 배경 속에서 차별받는 이민자들의 투쟁적 삶의 기록, 유배와 차별에 관한 이야기를 통해 우리에게 고향과 타향, 개인의 정체성이란 과연 무엇인지 질문한다. " +
                "작품에 등장하는 세 여성은 강인한 어머니이자 아내의 모습을 보여주며, 한편으로는 남편과 자식에게 헌신하는 전통적인 여성상이라는 굴레가 얼마나 한 여성의 삶을 안쓰럽게 만드는지도 보여준다.";
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME +
                "(" + COL_ID + " integer primary key autoincrement, " +
                COL_TITLE + " TEXT, " +
                COL_AUTHOR + " TEXT, " +
                COL_PUBLISHER + " TEXT, " +
                COL_SUMMARY + " TEXT, " +
                COL_PRICE + " TEXT, " +
                COL_IMAGE + " TEXT)";
        Log.d(TAG, sql);
        db.execSQL(sql);

        inputSample(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    private void inputSample(SQLiteDatabase db) {
        ContentValues row = new ContentValues();
        row.put(COL_TITLE, "1984");
        row.put(COL_AUTHOR, "조지오웰");
        row.put(COL_PUBLISHER, "민음사");
        row.put(COL_SUMMARY, summary_0);
        row.put(COL_PRICE, "9500");
        row.put(COL_IMAGE, R.mipmap.nineteeneigty);
        db.insert(TABLE_NAME, null, row);

        row.put(COL_TITLE, "죄와벌1");
        row.put(COL_AUTHOR, "도스토옙스키");
        row.put(COL_PUBLISHER, "문예출판사");
        row.put(COL_SUMMARY, summary_1);
        row.put(COL_PRICE, "10000");
        row.put(COL_IMAGE, R.mipmap.sin);
        db.insert(TABLE_NAME, null, row);

        row.put(COL_TITLE, "죄와벌2");
        row.put(COL_AUTHOR, "도스토옙스키");
        row.put(COL_PUBLISHER, "문예출판사");
        row.put(COL_SUMMARY, summary_2);
        row.put(COL_PRICE, "10000");
        row.put(COL_IMAGE, R.mipmap.sin2);
        db.insert(TABLE_NAME, null, row);

        row.put(COL_TITLE, "데미안");
        row.put(COL_AUTHOR, "헤르만 헤세");
        row.put(COL_PUBLISHER, "고래의숲");
        row.put(COL_SUMMARY, summary_3);
        row.put(COL_PRICE, "13000");
        row.put(COL_IMAGE, R.mipmap.demian);
        db.insert(TABLE_NAME, null, row);

        row.put(COL_TITLE, "날개");
        row.put(COL_AUTHOR, "이상");
        row.put(COL_PUBLISHER, "문학과지성사");
        row.put(COL_SUMMARY, summary_4);
        row.put(COL_PRICE, "12000");
        row.put(COL_IMAGE, R.mipmap.wing);
        db.insert(TABLE_NAME, null, row);

        row.put(COL_TITLE, "파친코1");
        row.put(COL_AUTHOR, "이민진");
        row.put(COL_PUBLISHER, "문학사상");
        row.put(COL_SUMMARY, summary_5);
        row.put(COL_PRICE, "14500");
        row.put(COL_IMAGE, R.mipmap.pachinko);
        db.insert(TABLE_NAME, null, row);

        row.put(COL_TITLE, "파친코2");
        row.put(COL_AUTHOR, "이민진");
        row.put(COL_PUBLISHER, "문학사상");
        row.put(COL_SUMMARY, summary_6);
        row.put(COL_PRICE, "14500");
        row.put(COL_IMAGE, R.mipmap.pachinko2);
        db.insert(TABLE_NAME, null, row);

        /*db.execSQL("insert into " + TABLE_NAME + " values (null, '1984', '조지오웰 ', '민음사', '', '9500');");
        db.execSQL("insert into " + TABLE_NAME + " values (null, '죄와벌', '도스토옙스키', '문예출판사', 'summary', '10000');");
        db.execSQL("insert into " + TABLE_NAME + " values (null, '데미안', '헤르만 헤세', '고래의숲', 'summary', '13000');");
        db.execSQL("insert into " + TABLE_NAME + " values (null, '날개', '이상', '문학과지성사', 'summary', '12000');");
        db.execSQL("insert into " + TABLE_NAME + " values (null, '파친코', '이민진', '문학사상', 'summary', '14500');");*/
    }
}
