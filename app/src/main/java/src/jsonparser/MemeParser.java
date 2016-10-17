package src.jsonparser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random; 

public class MemeParser {
	
//	public static void main(String[] args) {
//		String jsonData = readFile("crayola.json");
//		ArrayList<ArrayList<String>> finallySomething = memeInterface(jsonData);
//		System.out.println(finallySomething);
//
//	}

	public static String json = "[\n" +
			"\n" +
			"{\"emotions\":{\"contempt\":5.11696853E-4,\"surprise\":0.84235,\"happiness\":1.06476555E-5,\"neutral\":0.131396651,\"sadness\":4.01087338E-4,\"disgust\":3.45306122E-4,\"anger\":0.00399815058,\"fear\":0.0209864639},\"top\":\"Scooby-Doo Taught Us\",\"bottom\":\"That The Real Monsters Are Humans\"},{\"emotions\":{\"contempt\":4.6565017E-4,\"surprise\":0.809899867,\"happiness\":1.81411015E-5,\"neutral\":0.174631909,\"sadness\":6.572296E-4,\"disgust\":1.90037084E-4,\"anger\":0.00143874774,\"fear\":0.012698424},\"top\":\"Fired Bullets Are Called Slugs\",\"bottom\":\"Because They Have No Shell\"},{\"emotions\":{\"contempt\":4.6565017E-4,\"surprise\":0.809899867,\"happiness\":1.81411015E-5,\"neutral\":0.174631909,\"sadness\":6.572296E-4,\"disgust\":1.90037084E-4,\"anger\":0.00143874774,\"fear\":0.012698424},\"top\":\"Popcorn\",\"bottom\":\"Is A Vegetable\"},{\"emotions\":{\"contempt\":4.6565017E-4,\"surprise\":0.809899867,\"happiness\":1.81411015E-5,\"neutral\":0.174631909,\"sadness\":6.572296E-4,\"disgust\":1.90037084E-4,\"anger\":0.00143874774,\"fear\":0.012698424},\"top\":\"Cereal\",\"bottom\":\"Is A Soup\"},{\"emotions\":{\"contempt\":4.6565017E-4,\"surprise\":0.809899867,\"happiness\":1.81411015E-5,\"neutral\":0.174631909,\"sadness\":6.572296E-4,\"disgust\":1.90037084E-4,\"anger\":0.00143874774,\"fear\":0.012698424},\"top\":\"Holy Cow\",\"bottom\":\"That's Why Titanic Doesn't Have A Sequel\"},{\"emotions\":{\"contempt\":4.6565017E-4,\"surprise\":0.809899867,\"happiness\":1.81411015E-5,\"neutral\":0.174631909,\"sadness\":6.572296E-4,\"disgust\":1.90037084E-4,\"anger\":0.00143874774,\"fear\":0.012698424},\"top\":\"If An Astronaut Gave Birth In Space\",\"bottom\":\"The Baby Would Be An Alien\"},{\"emotions\":{\"contempt\":4.6565017E-4,\"surprise\":0.809899867,\"happiness\":1.81411015E-5,\"neutral\":0.174631909,\"sadness\":6.572296E-4,\"disgust\":1.90037084E-4,\"anger\":0.00143874774,\"fear\":0.012698424},\"top\":\"I Once Held The World Record\",\"bottom\":\"For Youngest Person On Earth\"},{\"emotions\":{\"contempt\":4.6565017E-4,\"surprise\":0.809899867,\"happiness\":1.81411015E-5,\"neutral\":0.174631909,\"sadness\":6.572296E-4,\"disgust\":1.90037084E-4,\"anger\":0.00143874774,\"fear\":0.012698424},\"top\":\"The Brain\",\"bottom\":\"Named Itself\"},{\"emotions\":{\"contempt\":4.6565017E-4,\"surprise\":0.809899867,\"happiness\":1.81411015E-5,\"neutral\":0.174631909,\"sadness\":6.572296E-4,\"disgust\":1.90037084E-4,\"anger\":0.00143874774,\"fear\":0.012698424},\"top\":\"Marketing\",\"bottom\":\"Is Professional Stereotyping\"},{\"emotions\":{\"contempt\":4.6565017E-4,\"surprise\":0.809899867,\"happiness\":1.81411015E-5,\"neutral\":0.174631909,\"sadness\":6.572296E-4,\"disgust\":1.90037084E-4,\"anger\":0.00143874774,\"fear\":0.012698424},\"top\":\"\\\"Vader\\\"\",\"bottom\":\"Is \\\"Father\\\" In Dutch\"},{\"emotions\":{\"contempt\":4.6565017E-4,\"surprise\":0.809899867,\"happiness\":1.81411015E-5,\"neutral\":0.174631909,\"sadness\":6.572296E-4,\"disgust\":1.90037084E-4,\"anger\":0.00143874774,\"fear\":0.012698424},\"top\":\"To Another Planet\",\"bottom\":\"We Are The Aliens\"},{\"emotions\":{\"contempt\":9.163724E-12,\"surprise\":9.621538E-5,\"happiness\":0.999903738,\"neutral\":6.087619E-10,\"sadness\":1.01186941E-12,\"disgust\":6.415303E-10,\"anger\":1.26727884E-9,\"fear\":2.20513652E-8},\"top\":\"I Sewed My Name On Your Shirts\",\"bottom\":\"In Case You Forget You're Taken\"},{\"emotions\":{\"contempt\":9.163724E-12,\"surprise\":9.621538E-5,\"happiness\":0.999903738,\"neutral\":6.087619E-10,\"sadness\":1.01186941E-12,\"disgust\":6.415303E-10,\"anger\":1.26727884E-9,\"fear\":2.20513652E-8},\"top\":\"You Say Restraining Order\",\"bottom\":\"I Say Long Distance Relationship\"},{\"emotions\":{\"contempt\":9.163724E-12,\"surprise\":9.621538E-5,\"happiness\":0.999903738,\"neutral\":6.087619E-10,\"sadness\":1.01186941E-12,\"disgust\":6.415303E-10,\"anger\":1.26727884E-9,\"fear\":2.20513652E-8},\"top\":\"I Went On Google Earth Last Night\",\"bottom\":\"Whose Car Was In Your Driveway July 17th, 2009?\"},{\"emotions\":{\"contempt\":9.163724E-12,\"surprise\":9.621538E-5,\"happiness\":0.999903738,\"neutral\":6.087619E-10,\"sadness\":1.01186941E-12,\"disgust\":6.415303E-10,\"anger\":1.26727884E-9,\"fear\":2.20513652E-8},\"top\":\"I Sold Your iPhone\",\"bottom\":\"I Don't Want You Talking To Siri\"},{\"emotions\":{\"contempt\":9.163724E-12,\"surprise\":9.621538E-5,\"happiness\":0.999903738,\"neutral\":6.087619E-10,\"sadness\":1.01186941E-12,\"disgust\":6.415303E-10,\"anger\":1.26727884E-9,\"fear\":2.20513652E-8},\"top\":\"I Burnt Your Home\",\"bottom\":\"So We Can Live Together\"},{\"emotions\":{\"contempt\":9.163724E-12,\"surprise\":9.621538E-5,\"happiness\":0.999903738,\"neutral\":6.087619E-10,\"sadness\":1.01186941E-12,\"disgust\":6.415303E-10,\"anger\":1.26727884E-9,\"fear\":2.20513652E-8},\"top\":\"\\\"Stalker\\\" Is Such A Mean Word\",\"bottom\":\"I Prefer \\\"Social Media Analyst\\\"\"},{\"emotions\":{\"contempt\":1.98374313E-8,\"surprise\":0.004418338,\"happiness\":5.075124E-4,\"neutral\":1.37259458E-5,\"sadness\":5.29820158E-4,\"disgust\":1.01146885E-4,\"anger\":0.9907652,\"fear\":0.00366420951},\"top\":\"I'll Go to Anger Management\",\"bottom\":\"When You Go To Stupid Management\"},{\"emotions\":{\"contempt\":1.98374313E-8,\"surprise\":0.004418338,\"happiness\":5.075124E-4,\"neutral\":1.37259458E-5,\"sadness\":5.29820158E-4,\"disgust\":1.01146885E-4,\"anger\":0.9907652,\"fear\":0.00366420951},\"top\":\"Anger Issues?\",\"bottom\":\"I've Never Been Happier\"},{\"emotions\":{\"contempt\":1.98374313E-8,\"surprise\":0.004418338,\"happiness\":5.075124E-4,\"neutral\":1.37259458E-5,\"sadness\":5.29820158E-4,\"disgust\":1.01146885E-4,\"anger\":0.9907652,\"fear\":0.00366420951},\"top\":\"What\",\"bottom\":\"Do You Want???\"},{\"emotions\":{\"contempt\":0.00203939714,\"surprise\":0.007815826,\"happiness\":4.61765183E-7,\"neutral\":0.7259518,\"sadness\":0.00118521694,\"disgust\":9.197615E-4,\"anger\":0.261200845,\"fear\":8.867152E-4},\"top\":\"You're In Love With Me\",\"bottom\":\"False, Love Is Not A Place And I Would Not Go There With You\"},{\"emotions\":{\"contempt\":0.00203939714,\"surprise\":0.007815826,\"happiness\":4.61765183E-7,\"neutral\":0.7259518,\"sadness\":0.00118521694,\"disgust\":9.197615E-4,\"anger\":0.261200845,\"fear\":8.867152E-4},\"top\":\"Age Is Just A Number\",\"bottom\":\"False: Age Is A Word\"},{\"emotions\":{\"contempt\":0.00203939714,\"surprise\":0.007815826,\"happiness\":4.61765183E-7,\"neutral\":0.7259518,\"sadness\":0.00118521694,\"disgust\":9.197615E-4,\"anger\":0.261200845,\"fear\":8.867152E-4},\"top\":\"It Takes Forever To Say Goodbye\",\"bottom\":\"False. It Takes .978 Seconds\"},{\"emotions\":{\"contempt\":0.00203939714,\"surprise\":0.007815826,\"happiness\":4.61765183E-7,\"neutral\":0.7259518,\"sadness\":0.00118521694,\"disgust\":9.197615E-4,\"anger\":0.261200845,\"fear\":8.867152E-4},\"top\":\"Life Is Short?\",\"bottom\":\"False. Living Is The Longest Thing You Will Ever Do.\"},{\"emotions\":{\"contempt\":0.00203939714,\"surprise\":0.007815826,\"happiness\":4.61765183E-7,\"neutral\":0.7259518,\"sadness\":0.00118521694,\"disgust\":9.197615E-4,\"anger\":0.261200845,\"fear\":8.867152E-4},\"top\":\"I'm The Closest Thing To Your Heart?\",\"bottom\":\"False. Your Lungs Are Closer.\"},{\"emotions\":{\"contempt\":0.00203939714,\"surprise\":0.007815826,\"happiness\":4.61765183E-7,\"neutral\":0.7259518,\"sadness\":0.00118521694,\"disgust\":9.197615E-4,\"anger\":0.261200845,\"fear\":8.867152E-4},\"top\":\"Love Is All You Need?\",\"bottom\":\"False. You Need Food And Water.\"},{\"emotions\":{\"contempt\":0.00363058178,\"surprise\":9.478085E-6,\"happiness\":9.00591459E-8,\"neutral\":0.001907167,\"sadness\":0.181909069,\"disgust\":0.7236219,\"anger\":0.08891568,\"fear\":6.03674971E-6},\"top\":\"Look At My Face\",\"bottom\":\"You Disgust Me\"},{\"emotions\":{\"contempt\":1.69921317E-4,\"surprise\":0.00154921308,\"happiness\":0.01854375,\"neutral\":0.00249814265,\"sadness\":0.559860349,\"disgust\":3.94827279E-4,\"anger\":6.34206444E-5,\"fear\":0.416920364},\"top\":\"Can I Please Come Over\",\"bottom\":\"And Whine To You In Person?\"},{\"emotions\":{\"contempt\":1.07184583E-14,\"surprise\":2.32011155E-12,\"happiness\":1.78415911E-11,\"neutral\":3.01646624E-11,\"sadness\":1,\"disgust\":3.69375364E-10,\"anger\":1.21144813E-11,\"fear\":1.19614346E-10},\"top\":\"Shut Up!\",\"bottom\":\"It's Not Funny!\"},{\"emotions\":{\"contempt\":1.07184583E-14,\"surprise\":2.32011155E-12,\"happiness\":1.78415911E-11,\"neutral\":3.01646624E-11,\"sadness\":1,\"disgust\":3.69375364E-10,\"anger\":1.21144813E-11,\"fear\":1.19614346E-10},\"top\":\"When you realize\",\"bottom\":\"You're On the Last Episode of Stranger Things\"},{\"emotions\":{\"contempt\":1.07184583E-14,\"surprise\":2.32011155E-12,\"happiness\":1.78415911E-11,\"neutral\":3.01646624E-11,\"sadness\":1,\"disgust\":3.69375364E-10,\"anger\":1.21144813E-11,\"fear\":1.19614346E-10},\"top\":\"Bites Into Cookie\",\"bottom\":\"Oatmeal Raisin\"},{\"emotions\":{\"contempt\":1.07184583E-14,\"surprise\":2.32011155E-12,\"happiness\":1.78415911E-11,\"neutral\":3.01646624E-11,\"sadness\":1,\"disgust\":3.69375364E-10,\"anger\":1.21144813E-11,\"fear\":1.19614346E-10},\"top\":\"Wants To Order Pizza\",\"bottom\":\"Everything's Closed at 3 A.M.\"},{\"emotions\":{\"contempt\":1.07184583E-14,\"surprise\":2.32011155E-12,\"happiness\":1.78415911E-11,\"neutral\":3.01646624E-11,\"sadness\":1,\"disgust\":3.69375364E-10,\"anger\":1.21144813E-11,\"fear\":1.19614346E-10},\"top\":\"Bought A New Laptop\",\"bottom\":\"Came With Windows 8\"},{\"emotions\":{\"contempt\":1.07184583E-14,\"surprise\":2.32011155E-12,\"happiness\":1.78415911E-11,\"neutral\":3.01646624E-11,\"sadness\":1,\"disgust\":3.69375364E-10,\"anger\":1.21144813E-11,\"fear\":1.19614346E-10},\"top\":\"Went Out With Friends\",\"bottom\":\"Missed Favorite TV Show\"},{\"emotions\":{\"contempt\":1.07184583E-14,\"surprise\":2.32011155E-12,\"happiness\":1.78415911E-11,\"neutral\":3.01646624E-11,\"sadness\":1,\"disgust\":3.69375364E-10,\"anger\":1.21144813E-11,\"fear\":1.19614346E-10},\"top\":\"Pokemon Popped Up\",\"bottom\":\"Rattata\"},{\"emotions\":{\"contempt\":1.07184583E-14,\"surprise\":2.32011155E-12,\"happiness\":1.78415911E-11,\"neutral\":3.01646624E-11,\"sadness\":1,\"disgust\":3.69375364E-10,\"anger\":1.21144813E-11,\"fear\":1.19614346E-10},\"top\":\"Just Wants To Eat\",\"bottom\":\"Food Too Hot\"},{\"emotions\":{\"contempt\":1.07184583E-14,\"surprise\":2.32011155E-12,\"happiness\":1.78415911E-11,\"neutral\":3.01646624E-11,\"sadness\":1,\"disgust\":3.69375364E-10,\"anger\":1.21144813E-11,\"fear\":1.19614346E-10},\"top\":\"Wants To See Video\",\"bottom\":\"Doesn't Want To Stop Listening To Music\"},{\"emotions\":{\"contempt\":2.86916957E-9,\"surprise\":1.851608E-7,\"happiness\":0.999999642,\"neutral\":1.81833855E-7,\"sadness\":1.73842038E-10,\"disgust\":1.97423744E-9,\"anger\":5.3763255E-10,\"fear\":2.33790747E-11},\"top\":\"I'm Condescending\",\"bottom\":\"That Means I Talk Down To You\"},{\"emotions\":{\"contempt\":2.86916957E-9,\"surprise\":1.851608E-7,\"happiness\":0.999999642,\"neutral\":1.81833855E-7,\"sadness\":1.73842038E-10,\"disgust\":1.97423744E-9,\"anger\":5.3763255E-10,\"fear\":2.33790747E-11},\"top\":\"Oh, You Hate Mondays?\",\"bottom\":\"I've Never Heard Of That Feeling Before. Do Go On.\"},{\"emotions\":{\"contempt\":1.32439687E-4,\"surprise\":7.25259156E-7,\"happiness\":6.77435352E-8,\"neutral\":0.06009701,\"sadness\":2.19220674E-5,\"disgust\":1.09514995E-5,\"anger\":0.9397366,\"fear\":3.01686669E-7},\"top\":\"4 Day Weekend?\",\"bottom\":\"You'll Have Plenty Of Time To Do 4x The Homework\"},{\"emotions\":{\"contempt\":1.44355013E-7,\"surprise\":1.40307193E-5,\"happiness\":0.9999005,\"neutral\":1.39571048E-5,\"sadness\":3.30120884E-6,\"disgust\":5.65173832E-5,\"anger\":1.15143985E-5,\"fear\":1.12222782E-8},\"top\":\"Puzzle Says 2 to 4 Years\",\"bottom\":\"Only Took One\"},{\"emotions\":{\"contempt\":4.326438E-8,\"surprise\":0.396051854,\"happiness\":1.35110065E-6,\"neutral\":3.963894E-6,\"sadness\":6.503775E-5,\"disgust\":2.852741E-5,\"anger\":0.0108316634,\"fear\":0.5930176},\"top\":\"What\",\"bottom\":\"Have I Done???\"},{\"emotions\":{\"contempt\":1.130996E-5,\"surprise\":6.43761737E-8,\"happiness\":2.19319059E-4,\"neutral\":6.41155464E-4,\"sadness\":0.999043941,\"disgust\":8.35720057E-5,\"anger\":3.96540742E-7,\"fear\":2.35546551E-7},\"top\":\"Please Don't Leave Me Alone...\",\"bottom\":\"I'm Scared!\"},{\"emotions\":{\"contempt\":1.130996E-5,\"surprise\":6.43761737E-8,\"happiness\":2.19319059E-4,\"neutral\":6.41155464E-4,\"sadness\":0.999043941,\"disgust\":8.35720057E-5,\"anger\":3.96540742E-7,\"fear\":2.35546551E-7},\"top\":\"My Hand Is Too Fat For The Pringles Container...\",\"bottom\":\"So I Have To Tilt It\"},{\"emotions\":{\"contempt\":6.92322734E-4,\"surprise\":0.185696,\"happiness\":9.342837E-5,\"neutral\":0.00132684247,\"sadness\":0.00116258906,\"disgust\":0.02568865,\"anger\":0.3156558,\"fear\":0.469684362},\"top\":\"When Your Battery Is At 2%\",\"bottom\":\"And There's No Charger In Sight\"},{\"emotions\":{\"contempt\":8.58930463E-4,\"surprise\":0.5618151,\"happiness\":1.46162006E-6,\"neutral\":0.0389441773,\"sadness\":0.00188244961,\"disgust\":0.004254596,\"anger\":0.00988664851,\"fear\":0.382356644},\"top\":\"\",\"bottom\":\"Oh Shit\"},{\"emotions\":{\"contempt\":8.58930463E-4,\"surprise\":0.5618151,\"happiness\":1.46162006E-6,\"neutral\":0.0389441773,\"sadness\":0.00188244961,\"disgust\":0.004254596,\"anger\":0.00988664851,\"fear\":0.382356644},\"top\":\"Oh Shit\",\"bottom\":\"\"},{\"emotions\":{\"contempt\":0.00355751836,\"surprise\":0.0123421093,\"happiness\":1.00236473E-4,\"neutral\":0.006705215,\"sadness\":0.161219642,\"disgust\":0.6021314,\"anger\":0.04915721,\"fear\":0.164786652},\"top\":\"You Disgust\",\"bottom\":\"Me\"},{\"emotions\":{\"contempt\":0.00355751836,\"surprise\":0.0123421093,\"happiness\":1.00236473E-4,\"neutral\":0.006705215,\"sadness\":0.161219642,\"disgust\":0.6021314,\"anger\":0.04915721,\"fear\":0.164786652},\"top\":\"Oh No You Didn't\",\"bottom\":\"*Z-Snap*\"},{\"emotions\":{\"contempt\":0.00355751836,\"surprise\":0.0123421093,\"happiness\":1.00236473E-4,\"neutral\":0.006705215,\"sadness\":0.161219642,\"disgust\":0.6021314,\"anger\":0.04915721,\"fear\":0.164786652},\"top\":\"\",\"bottom\":\"Do Not Want\"},{\"emotions\":{\"contempt\":0.00355751836,\"surprise\":0.0123421093,\"happiness\":1.00236473E-4,\"neutral\":0.006705215,\"sadness\":0.161219642,\"disgust\":0.6021314,\"anger\":0.04915721,\"fear\":0.164786652},\"top\":\"That Squid Is So Undercooked That\",\"bottom\":\"I Can Still Hear Him Telling Spongebob To F-off\"},{\"emotions\":{\"contempt\":0.00355751836,\"surprise\":0.0123421093,\"happiness\":1.00236473E-4,\"neutral\":0.006705215,\"sadness\":0.161219642,\"disgust\":0.6021314,\"anger\":0.04915721,\"fear\":0.164786652},\"top\":\"Oh...\",\"bottom\":\"That's Nasty\"},{\"emotions\":{\"contempt\":0.00355751836,\"surprise\":0.0123421093,\"happiness\":1.00236473E-4,\"neutral\":0.006705215,\"sadness\":0.161219642,\"disgust\":0.6021314,\"anger\":0.04915721,\"fear\":0.164786652},\"top\":\"The Moment You See\",\"bottom\":\"Two Teachers Flirting\"},{\"emotions\":{\"contempt\":6.92322734E-4,\"surprise\":0.185696,\"happiness\":9.342837E-5,\"neutral\":0.00132684247,\"sadness\":0.00116258906,\"disgust\":0.02568865,\"anger\":0.3156558,\"fear\":0.469684362},\"top\":\"When The Stress \",\"bottom\":\"Is Real!!!\"},{\"emotions\":{\"contempt\":6.92322734E-4,\"surprise\":0.185696,\"happiness\":9.342837E-5,\"neutral\":0.00132684247,\"sadness\":0.00116258906,\"disgust\":0.02568865,\"anger\":0.3156558,\"fear\":0.469684362},\"top\":\"When The Fear \",\"bottom\":\"Is Real\"},{\"emotions\":{\"contempt\":0.426464349,\"surprise\":0.0128901554,\"happiness\":0.08345109,\"neutral\":0.189094931,\"sadness\":0.00519613735,\"disgust\":0.195833951,\"anger\":0.08671725,\"fear\":3.52131668E-4},\"top\":\"One Does Not Simply\",\"bottom\":\"Drink Milk From The Carton\"},{\"emotions\":{\"contempt\":0.426464349,\"surprise\":0.0128901554,\"happiness\":0.08345109,\"neutral\":0.189094931,\"sadness\":0.00519613735,\"disgust\":0.195833951,\"anger\":0.08671725,\"fear\":3.52131668E-4},\"top\":\"One Does Not Simply\",\"bottom\":\"Find Nemo\"},{\"emotions\":{\"contempt\":0.426464349,\"surprise\":0.0128901554,\"happiness\":0.08345109,\"neutral\":0.189094931,\"sadness\":0.00519613735,\"disgust\":0.195833951,\"anger\":0.08671725,\"fear\":3.52131668E-4},\"top\":\"One Does Not Simply\",\"bottom\":\"Rise And Shine\"},{\"emotions\":{\"contempt\":0.426464349,\"surprise\":0.0128901554,\"happiness\":0.08345109,\"neutral\":0.189094931,\"sadness\":0.00519613735,\"disgust\":0.195833951,\"anger\":0.08671725,\"fear\":3.52131668E-4},\"top\":\"One Does Not Simply\",\"bottom\":\"Answer Free Response Questions\"},{\"emotions\":{\"contempt\":0.426464349,\"surprise\":0.0128901554,\"happiness\":0.08345109,\"neutral\":0.189094931,\"sadness\":0.00519613735,\"disgust\":0.195833951,\"anger\":0.08671725,\"fear\":3.52131668E-4},\"top\":\"One Does Not Simply\",\"bottom\":\"Get Off The Internet\"},{\"emotions\":{\"contempt\":0.426464349,\"surprise\":0.0128901554,\"happiness\":0.08345109,\"neutral\":0.189094931,\"sadness\":0.00519613735,\"disgust\":0.195833951,\"anger\":0.08671725,\"fear\":3.52131668E-4},\"top\":\"One Does Not Simply\",\"bottom\":\"Read The Terms And Conditions\"},{\"emotions\":{\"contempt\":0.426464349,\"surprise\":0.0128901554,\"happiness\":0.08345109,\"neutral\":0.189094931,\"sadness\":0.00519613735,\"disgust\":0.195833951,\"anger\":0.08671725,\"fear\":3.52131668E-4},\"top\":\"One Does Not Simply\",\"bottom\":\"Step On A Lego And Feel No Pain\"},{\"emotions\":{\"contempt\":0.426464349,\"surprise\":0.0128901554,\"happiness\":0.08345109,\"neutral\":0.189094931,\"sadness\":0.00519613735,\"disgust\":0.195833951,\"anger\":0.08671725,\"fear\":3.52131668E-4},\"top\":\"One Does Not Simply\",\"bottom\":\"Invade Russia During Winter\"},{\"emotions\":{\"contempt\":9.79565E-6,\"surprise\":3.87030667E-7,\"happiness\":8.30788E-6,\"neutral\":0.99998,\"sadness\":1.48189235E-6,\"disgust\":1.81339321E-9,\"anger\":3.94320843E-8,\"fear\":3.89640264E-10},\"top\":\"I Cannot Be Held Responsible\",\"bottom\":\"For What My Face Does When You Talk\"},{\"emotions\":{\"contempt\":9.79565E-6,\"surprise\":3.87030667E-7,\"happiness\":8.30788E-6,\"neutral\":0.99998,\"sadness\":1.48189235E-6,\"disgust\":1.81339321E-9,\"anger\":3.94320843E-8,\"fear\":3.89640264E-10},\"top\":\"Its Not My Fault\",\"bottom\":\"I Look Like An Angry Serial Killer\"},{\"emotions\":{\"contempt\":9.79565E-6,\"surprise\":3.87030667E-7,\"happiness\":8.30788E-6,\"neutral\":0.0098,\"sadness\":1.48189235E-6,\"disgust\":0.99,\"anger\":3.94320843E-8,\"fear\":3.89640264E-10},\"top\":\"When Someone Blows Their Nose\",\"bottom\":\"While You Are Trying To Eat\"},{\"emotions\":{\"contempt\":9.79565E-6,\"surprise\":3.87030667E-7,\"happiness\":8.30788E-6,\"neutral\":0.99998,\"sadness\":1.48189235E-6,\"disgust\":1.81339321E-9,\"anger\":3.94320843E-8,\"fear\":3.89640264E-10},\"top\":\"That Was So Funny\",\"bottom\":\"I Laughed\"},{\"emotions\":{\"contempt\":5.361899E-13,\"surprise\":2.86293E-7,\"happiness\":0.9999997,\"neutral\":1.09284623E-10,\"sadness\":5.20471843E-9,\"disgust\":7.583484E-10,\"anger\":1.15670113E-8,\"fear\":5.88057436E-10},\"top\":\"Has Pet Rock\",\"bottom\":\"Runs Away\"},{\"emotions\":{\"contempt\":5.361899E-13,\"surprise\":2.86293E-7,\"happiness\":0.9999997,\"neutral\":1.09284623E-10,\"sadness\":5.20471843E-9,\"disgust\":7.583484E-10,\"anger\":1.15670113E-8,\"fear\":5.88057436E-10},\"top\":\"Spends All Night Studying\",\"bottom\":\"Sleeps Through Exam\"},{\"emotions\":{\"contempt\":5.361899E-13,\"surprise\":2.86293E-7,\"happiness\":0.9999997,\"neutral\":1.09284623E-10,\"sadness\":5.20471843E-9,\"disgust\":7.583484E-10,\"anger\":1.15670113E-8,\"fear\":5.88057436E-10},\"top\":\"Escapes Burning Building\",\"bottom\":\"Gets Hit By Firetruck\"},{\"emotions\":{\"contempt\":5.361899E-13,\"surprise\":2.86293E-7,\"happiness\":0.9999997,\"neutral\":1.09284623E-10,\"sadness\":5.20471843E-9,\"disgust\":7.583484E-10,\"anger\":1.15670113E-8,\"fear\":5.88057436E-10},\"top\":\"Takes SAT\",\"bottom\":\"Forgot To Use Number 2 Pencil\"},{\"emotions\":{\"contempt\":5.361899E-13,\"surprise\":2.86293E-7,\"happiness\":0.9999997,\"neutral\":1.09284623E-10,\"sadness\":5.20471843E-9,\"disgust\":7.583484E-10,\"anger\":1.15670113E-8,\"fear\":5.88057436E-10},\"top\":\"Scores First Goal\",\"bottom\":\"Own Goal\"},{\"emotions\":{\"contempt\":5.361899E-13,\"surprise\":2.86293E-7,\"happiness\":0.9999997,\"neutral\":1.09284623E-10,\"sadness\":5.20471843E-9,\"disgust\":7.583484E-10,\"anger\":1.15670113E-8,\"fear\":5.88057436E-10},\"top\":\"Starts Own Business\",\"bottom\":\"Gets Fired\"},{\"emotions\":{\"contempt\":8.84521E-5,\"surprise\":6.5368E-7,\"happiness\":0.00193156721,\"neutral\":0.0035981338,\"sadness\":0.993953347,\"disgust\":4.22864425E-4,\"anger\":3.597147E-6,\"fear\":1.36389929E-6},\"top\":\"Right Now\",\"bottom\":\"I Am Feeling\"}, {\"emotions\":{\"contempt\":6.92322734E-4,\"surprise\":0.185696,\"happiness\":9.342837E-5,\"neutral\":0.00132684247,\"sadness\":0.00116258906,\"disgust\":0.02568865,\"anger\":0.3156558,\"fear\":0.469684362},\"top\":\"When The Fear \",\"bottom\":\"Is Real\"},{\"emotions\":{\"contempt\":8.84521E-5,\"surprise\":6.5368E-7,\"happiness\":0.00193156721,\"neutral\":0.0035981338,\"sadness\":0.993953347,\"disgust\":4.22864425E-4,\"anger\":3.597147E-6,\"fear\":1.36389929E-6},\"top\":\"Don't Wanna Be\",\"bottom\":\"Owl By Myself\"},{\"emotions\":{\"contempt\":8.84521E-5,\"surprise\":6.5368E-7,\"happiness\":0.00193156721,\"neutral\":0.0035981338,\"sadness\":0.993953347,\"disgust\":4.22864425E-4,\"anger\":3.597147E-6,\"fear\":1.36389929E-6},\"top\":\"I Spend My Life Trying To Make A Meme\",\"bottom\":\"I Just Become One\"},{\"emotions\":{\"contempt\":8.84521E-5,\"surprise\":6.5368E-7,\"happiness\":0.00193156721,\"neutral\":0.0035981338,\"sadness\":0.993953347,\"disgust\":4.22864425E-4,\"anger\":3.597147E-6,\"fear\":1.36389929E-6},\"top\":\"My Flat Screen Is Too Thin\",\"bottom\":\"To Support A Wii Sensor Bar On Top\"},{\"emotions\":{\"contempt\":8.84521E-5,\"surprise\":6.5368E-7,\"happiness\":0.00193156721,\"neutral\":0.0035981338,\"sadness\":0.993953347,\"disgust\":4.22864425E-4,\"anger\":3.597147E-6,\"fear\":1.36389929E-6},\"top\":\"Don't Go\",\"bottom\":\"I'll Be Sad\"},{\"emotions\":{\"contempt\":8.84521E-5,\"surprise\":6.5368E-7,\"happiness\":0.00193156721,\"neutral\":0.0035981338,\"sadness\":0.993953347,\"disgust\":4.22864425E-4,\"anger\":3.597147E-6,\"fear\":1.36389929E-6},\"top\":\"Don't Wanna Be\",\"bottom\":\"O-Ba_Ma Self\"},{\"emotions\":{\"contempt\":8.84521E-5,\"surprise\":6.5368E-7,\"happiness\":0.00193156721,\"neutral\":0.0035981338,\"sadness\":0.993953347,\"disgust\":4.22864425E-4,\"anger\":3.597147E-6,\"fear\":1.36389929E-6},\"top\":\"Like The Trees\",\"bottom\":\"I Stand Alone\"},{\"emotions\":{\"contempt\":8.84521E-5,\"surprise\":6.5368E-7,\"happiness\":0.00193156721,\"neutral\":0.0035981338,\"sadness\":0.993953347,\"disgust\":4.22864425E-4,\"anger\":3.597147E-6,\"fear\":1.36389929E-6},\"top\":\"I Just Wanted To Say...\",\"bottom\":\"Amish You\"},{\"emotions\":{\"contempt\":0.00155398983,\"surprise\":0.307374746,\"happiness\":1.0677029E-6,\"neutral\":0.0253461823,\"sadness\":0.0335076377,\"disgust\":0.012388288,\"anger\":0.004097882,\"fear\":0.6157302},\"top\":\"Thunderbolt And Lightning \",\"bottom\":\"Very Very Frightening Me!\"},{\"emotions\":{\"contempt\":0.00155398983,\"surprise\":0.307374746,\"happiness\":1.0677029E-6,\"neutral\":0.0253461823,\"sadness\":0.0335076377,\"disgust\":0.012388288,\"anger\":0.004097882,\"fear\":0.6157302},\"top\":\"Oh, I'm So Scared\",\"bottom\":\"Please Don't Hurt Me With Your Big Words\"},{\"emotions\":{\"contempt\":0.00155398983,\"surprise\":0.307374746,\"happiness\":1.0677029E-6,\"neutral\":0.0253461823,\"sadness\":0.0335076377,\"disgust\":0.012388288,\"anger\":0.004097882,\"fear\":0.6157302},\"top\":\"I See\",\"bottom\":\"Dead People\"},{\"emotions\":{\"contempt\":0.00155398983,\"surprise\":0.307374746,\"happiness\":1.0677029E-6,\"neutral\":0.0253461823,\"sadness\":0.0335076377,\"disgust\":0.012388288,\"anger\":0.004097882,\"fear\":0.6157302},\"top\":\"I'm Only Scared Of\",\"bottom\":\"Everything\"}]";

	 public static ArrayList<ArrayList<String>> memeInterface(String JSONString) {
		 HashMap<String, ArrayList<ArrayList<String>>> aMap = getMemes("bigList.json"); 
		 String emote = getEmote(JSONString);
		 ArrayList<ArrayList<String>> anArray = getSomeMemes(emote,aMap);
		 return anArray;
		 
	        }
	        
	 public static String getEmote(String JSONString){
		 try {
			 JSONArray jarr = new JSONArray(JSONString);
			 for (int i = 0; i < jarr.length(); i++) {
				 JSONObject jobj = jarr.getJSONObject(i);
				 JSONObject jobjemote = jobj.getJSONObject("scores");
				 double max = 0;
				 String maxKey = "";
				 for (Iterator iterator = jobjemote.keys(); iterator.hasNext(); ) {
					 String key = (String) iterator.next();
					 // System.out.println(jobjemote);
					 double checkNum = jobjemote.getDouble(key);
					 if (checkNum > max) {
						 max = checkNum;
						 maxKey = key;
					 }
				 }
				 return maxKey;

			 }
		 } catch (Exception e){
			 e.printStackTrace();
		 }
		return "";
	 }
	 
	 public static HashMap<String, ArrayList<ArrayList<String>>> getMemes(String fileName) {
		 String jsonData = json;//readFile(fileName);
		 jsonData = json;
		 HashMap<String, ArrayList<ArrayList<String>>> map = new HashMap<String, ArrayList<ArrayList<String>>>();
		 try {

			 JSONArray jarr = new JSONArray(jsonData);


			 //   System.out.println(jarr.length());

			 for (int i = 0; i < jarr.length(); i++) {
				 JSONObject jobj = jarr.getJSONObject(i);
				 JSONObject jobjemote = jobj.getJSONObject("emotions");
				 String topText = jobj.getString("top");
				 String btmText = jobj.getString("bottom");
				 double max = 0;
				 String maxKey = "";
				 for (Iterator iterator = jobjemote.keys(); iterator.hasNext(); ) {
					 String key = (String) iterator.next();
					 // System.out.println(jobjemote);
					 double checkNum = jobjemote.getDouble(key);
					 if (checkNum > max) {
						 max = checkNum;
						 maxKey = key;
					 }
				 }
				 //System.out.println(topText + "\n" + btmText + "\n" + max + "\n" + maxKey);

				 ArrayList<String> text = new ArrayList();
				 text.add(topText);
				 text.add(btmText);
				 if (map.containsKey(maxKey)) {
					 ArrayList<ArrayList<String>> listOfText = map.get(maxKey);
					 listOfText.add(text);
				 } else {
					 ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
					 list.add(text);
					 map.put(maxKey, list);
				 }
			 }
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
        	//System.out.println(map);
        	return map;
	 }
	    


	    public static ArrayList<ArrayList<String>> getSomeMemes(String emotion, HashMap<String, ArrayList<ArrayList<String>>> map)

	    {
	    	
	    	//System.out.println(emotion);
	    ArrayList<ArrayList<String>> memes = map.get(emotion);

	    

	    if(memes.size() < 3)

	    return memes;
	    
	    else

	    {

	    Random rgen = new Random();

	        ArrayList<ArrayList<String>> newMemes = new ArrayList<ArrayList<String>>();

	        ArrayList<Integer> addedIndices = new ArrayList();

	    while(newMemes.size() < 3)

	    {

	            int random = rgen.nextInt(memes.size()-1);

	            if(!addedIndices.contains(random))

	            {

	                addedIndices.add(random);

	                ArrayList<String> meme = memes.get(random);

	                newMemes.add(meme);

	            }

	    }

	    return newMemes;

	    }

	    }
	    
	 
	 
	    public static String readFile(String filename) {
	    	String result = "";
	        try {
	            BufferedReader br = new BufferedReader(new FileReader(filename));
	            StringBuilder sb = new StringBuilder();
	            String line = br.readLine();
	            while (line != null) {
	                sb.append(line);
	                line = br.readLine();
	            }
	            result = sb.toString();
	        } catch(Exception e) {
	            e.printStackTrace();
	        }
	        return result;
	        
	    }
	 }
