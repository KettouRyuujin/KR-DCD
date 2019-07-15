package dcdmod.Card.SelectCard;

import java.util.ArrayList;
import java.util.List;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import basemod.helpers.TooltipInfo;
import dcdmod.DCDmod;
import dcdmod.Patches.AbstractCardEnum;



public class BladeSlash_s extends CustomCard{
	
	public static final String ID = "BladeSlash_s";
	public static final String IMG_PATH = "img/1024/orb-dark.png";
	private static final int COST = -2;
	private static final CardStrings cardStrings;
	public static final String[] EXTENDED_DESCRIPTION;
	private List<TooltipInfo> tips;
	
	
	public BladeSlash_s() {
		super(ID, " ", IMG_PATH, COST, " ",
        		AbstractCard.CardType.SKILL, AbstractCardEnum.DCD,
        		AbstractCard.CardRarity.BASIC, AbstractCard.CardTarget.SELF);
		this.tags.add(DCDmod.RiderCard);
		this.tags.add(DCDmod.SelectCard);
		this.tips = new ArrayList<TooltipInfo>();
		this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
		setBackgroundTexture("img/cards/BladeSlash_s.png", "img/cards/BladeSlash_s_p.png");
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {

	}
	
	@Override
	public List<TooltipInfo> getCustomTooltips() {
		return this.tips;
	}
	
	@Override
    public AbstractCard makeCopy() {
        return new BladeSlash_s();
    }
	
    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("BladeSlash_s");
        EXTENDED_DESCRIPTION = BladeSlash_s.cardStrings.EXTENDED_DESCRIPTION;
    }
	
	@Override
    public void upgrade() {
		if (!this.upgraded) {
		}
	
    }
    

	
	
}
