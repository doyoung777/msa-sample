package com.sk.sample.mall.payment.domain.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPurchase is a Querydsl query type for Purchase
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPurchase extends EntityPathBase<Purchase> {

    private static final long serialVersionUID = 1206402665L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPurchase purchase = new QPurchase("purchase");

    public final com.sk.sample.mall.shared.base.QAbstractEntity _super = new com.sk.sample.mall.shared.base.QAbstractEntity(this);

    public final QCreditCard creditCard;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Integer> purchasedAmount = createNumber("purchasedAmount", Integer.class);

    public final DateTimePath<java.util.Date> purchasedDate = createDateTime("purchasedDate", java.util.Date.class);

    public final BooleanPath successed = createBoolean("successed");

    public QPurchase(String variable) {
        this(Purchase.class, forVariable(variable), INITS);
    }

    public QPurchase(Path<? extends Purchase> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPurchase(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPurchase(PathMetadata metadata, PathInits inits) {
        this(Purchase.class, metadata, inits);
    }

    public QPurchase(Class<? extends Purchase> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.creditCard = inits.isInitialized("creditCard") ? new QCreditCard(forProperty("creditCard")) : null;
    }

}

