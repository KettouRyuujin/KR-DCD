package dcdmod.Actions;

import java.util.ArrayList;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class DCDenergyAciton {
	static ArrayList<AbstractCard> temp = new ArrayList<AbstractCard>();
	public static void change() {
	for(AbstractCard c : AbstractDungeon.player.hand.group) {
			temp.add(c);
		}
	AbstractDungeon.player.hand.group.clear();
	AbstractDungeon.player.hand.group = temp;
	}
	
}

