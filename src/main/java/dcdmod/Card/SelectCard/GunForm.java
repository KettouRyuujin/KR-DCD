package dcdmod.Card.SelectCard;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import dcdmod.DCDmod;
import dcdmod.Patches.AbstractCardEnum;



public class GunForm extends CustomCard{
	
	public static final String ID = "GunForm";
	public static final String IMG_PATH = "img/1024/orb-dark.png";
	private static final int COST = -2;

	
	
	public GunForm() {
		super(ID, " ", IMG_PATH, COST, " ",
        		AbstractCard.CardType.SKILL, AbstractCardEnum.DCD,
        		AbstractCard.CardRarity.BASIC, AbstractCard.CardTarget.SELF);
		this.tags.add(DCDmod.RiderCard);
		this.tags.add(DCDmod.SelectCard);
		setBackgroundTexture("img/cards/RideBooker_g.png", "img/cards/RideBooker_g_p.png");
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {

	}
	
	@Override
    public AbstractCard makeCopy() {
        return new GunForm();
    }
	
	
	@Override
    public void upgrade() {
		if (!this.upgraded) {
		}
	
    }
    

	
	
}
