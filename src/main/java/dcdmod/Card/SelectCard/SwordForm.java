package dcdmod.Card.SelectCard;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import dcdmod.DCDmod;
import dcdmod.Patches.AbstractCardEnum;



public class SwordForm extends CustomCard{
	
	public static final String ID = "SwordForm";
	public static final String IMG_PATH = "img/1024/orb-dark.png";
	private static final int COST = -2;

	
	
	public SwordForm() {
		super(ID, " ", IMG_PATH, COST, " ",
        		AbstractCard.CardType.SKILL, AbstractCardEnum.DCD,
        		AbstractCard.CardRarity.BASIC, AbstractCard.CardTarget.SELF);
		this.tags.add(DCDmod.RiderCard);
		this.tags.add(DCDmod.SelectCard);
		setBackgroundTexture("img/cards/RideBooker_s.png", "img/cards/RideBooker_s_p.png");
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {

	}
	
	@Override
    public AbstractCard makeCopy() {
        return new SwordForm();
    }
	
	
	@Override
    public void upgrade() {
		if (!this.upgraded) {
		}
	
    }
    

	
	
}
