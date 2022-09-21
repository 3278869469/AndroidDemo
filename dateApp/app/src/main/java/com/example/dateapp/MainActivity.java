package com.example.dateapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePicker.OnDateChangedListener {

    private int y, m, d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatePicker dp_test = (DatePicker) findViewById(R.id.date);
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int monthOfYear = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        dp_test.init(year, monthOfYear, dayOfMonth, this);

        Button btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(onClick);

    }

    @Override
    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        y = year;
        m = monthOfYear + 1;
        d = dayOfMonth;
//        Toast.makeText(MainActivity.this, "您选择的日期是：" + y + "年" + m + "月"
//                + d + "日 !", Toast.LENGTH_SHORT).show();
    }

    private View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            count();
        }
    };

    private void count() {
        int Talent_num = 0;
        int Life_num = 0;
        Talent_num += sum(y);
        Talent_num += sum(m);
        Talent_num += sum(d);

        Life_num = Talent_num;
        while (Life_num > 10) {
            Life_num = sum(Life_num);
        }
        ;

        Talent_num = Talent_num % 10;

        String s1;
        switch (Life_num) {
            case 1:
                s1 = "独立、主见、天生领袖、个性较急";
                break; //可选
            case 2:
                s1 = "信任别人、双重性格、在分析、色彩造型、艺术鉴赏、文学方面有优秀表现。 圈多：依赖、有生活品味";
                break; //可选
            case 3:
                s1 = "创意、艺术表达力，多愁善感型";
                break;
            case 4:
                s1 = "组织能力强、稳定";
                break;
            case 5:
                s1 = "爱好自由、口才好、美食家、旅行家、个性开朗";
                break;
            case 6:
                s1 = "负责、天生有治疗别人的能力，喜欢交朋友";
                break;
            case 7:
                s1 = "好奇心，求知欲强、追求真理";
                break;
            case 8:
                s1 = "生意、公关、人际开发能力强，最讨厌别人懒惰";
                break;
            default:
                s1 = "慈悲家、梦想家、与宗教有缘份圈多：服务高手（不能批评）";
                break;
        }

        String s2;
        switch (Life_num) {
            case 1:
                s2 = "独立与创造（主见、领袖自居)\n" +
                        "天生的领导人，有本事，喜标新立异，满脑子新点子，总在发掘新事务，比较主观及冲动价值观是非分明\n" +
                        "人生课题：学习信任与合作\n" +
                        "沟通之道：不能指挥他们做事做决定要咨话题他们，好处要让他们知道，在意价格\n" +
                        "适合的工作：有前瞻性，发展性的，旬立作业的工作，如市场开发，国外工作，部门主管，发明家\n";
                break; //可选
            case 2:
                s2 = "合作与细节（信任别人、擅长分析、双重性格)\n" +
                        "天生具有敏锐分析及观察允许角力，爱批评、擅指正，具男女双重性格，情感丰富，为人温和亲切，天生依赖\n" +
                        "人生课题：学习独立与耐心\n" +
                        "沟通之道：喜欢由您做决定，希望得到感激涕零，在意价格\n" +
                        "适合的工作：公关职务，协调沟通类型\n";
                break; //可选
            case 3:
                s2 = "创意与沟通（理想家和艺术家）\n" +
                        "聪明，天赋异禀的艺术家，充满理想，立于不败之地沟通，学习力强，太理想化，不够实际\n" +
                        "人生课题：学习实际与接爱他人的批评和建议\n" +
                        "沟通之道：不能批评，在乎别人对他的看法，相信美好的事物，不在意价格\n" +
                        "适合工作：可发挥创意的工作，如企划，音乐工作，演艺人员\n";
                break;
            case 4:
                s2 = "安全感与秩序（称定、踏实，保障）\n" +
                        "擅长看穿事情真相，有条理给人安全感，业于组织与生产，务实，稳定，可靠，天生缺乏安全感过于顽固，不喜欢改变\n" +
                        "人生课题：学习给自己安全感和接受改变\n" +
                        "沟通之道：眼见为凭，多讲保障，在意价格\n" +
                        "适合工作：组织建构力强的工作：如建筑师，工程师\n";
                break;
            case 5:
                s2 = "自由与冒险（不爱拘束）\n" +
                        "拥有演说与促销的天份，崇尚自由及品味，喜欢新奇人生课题：学习承诺与勇气\n" +
                        "沟通之道：不要逼上梁山他们做承诺，不能限制和压迫他们，\n" +
                        "适合工作：业务性质的工作如业务员，或投身自由职业的工作\n";
                break;
            case 6:
                s2 = "和谐与助人（勇于承诺和承担责任，照顾他人胜过照顾自己）\n" +
                        "乐于付出及承担，具有丰富的爱心，勇于分担他人困难拙于面对自己的困难，凡事应量力而为\n" +
                        "人生课题：学习拒绝，多爱自己\n" +
                        "沟通之道：喜欢肯定和被人需要，不能叫他们怎么做，而请他们协助会取得更大成就，他们会非常乐意，不在乎价格\n" +
                        "适合工作：服务性质的工作，如服务业，护士，咨询师\n";
                break;
            case 7:
                s2 = "分析与真理（爱质疑）\n" +
                        "直觉敏锐精于研究，调查，寻找事实的真相，能力强，除非百分百的肯定，否则对答案难满意，追求至高无上的真理及精神，多疑，较懒惰\n" +
                        "人生课题：学习相信与改变，接受真相\n" +
                        "沟通之道：让他们明确知道得到的好处，指出发展潜力，注重形象，开始在乎价格，除非是让其可以在未来获利\n" +
                        "适合工作：研究性质的工作，如教授，研发人员\n";
                break;
            case 8:
                s2 = "权力与开发（天生的生意、公关能力强）\n" +
                        "天生领导人，善于开发，资质聪明，独具慧眼，具建设力，相信白手起家，善用投机天份坐拥权力\n" +
                        "人生课题：学习对自己诚实也对别人诚实（如68有连线且有5时，则亲切诚实）\n" +
                        "沟通之道：让他们明确知道得到的好处，指出发展潜力，注重形象，开始在乎价格，除非是让其可以在未来获利\n" +
                        "适合工作：自我创业性质的工作，或投射政治界\n";
                break;
            default:
                s2 = "想象与人道（天使、服务人群）\n" +
                        "擅长服务、娱乐，照顾及帮助他人，拥有常人无法想象的能量，与宗教有缘份\n" +
                        "人生课题：学习专注于实现梦想和接受他人的批评和建议\n" +
                        "沟通之道：不能批评，用下面而轻松的方式表达，找出他们的梦想所在，并且扩大它，不在意价格\n" +
                        "适合工作适合不以营利为目的事业或工作（公益及义工）\n";
                break;
        }

        Toast.makeText(MainActivity.this, "\n生命数：" + Life_num + "," + s1 + "\n天赋数:" + Talent_num + "," + s2, Toast.LENGTH_SHORT).show();

    }

    ;

    //    各个位相加
    private int sum(int num) {
        int sum = 0;
        while (num != 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }
    ;


}
