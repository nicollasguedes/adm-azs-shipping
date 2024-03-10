package com.me.nicollas.admazsshipping.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ShipmentStatusEnum {


    //Pre-Shipment:
    LABEL_CREATED, ORDER_PLACED, SHIPMENT_PENDING,

    //In Transit:
    PICKED_UP, IN_TRANSIT, AT_TRANSIT_HUB,

    //Delivery:
    DELIVERED, DELIVERY_ATTEMPTED, DELIVERED_HELD_AT_LOCATION,

    //Exceptions:
    SHIPMENT_EXCEPTION, RETURN_TO_SENDER, LOST,

    //Additional Statuses:
    MANIFEST_CREATED, AVAILABLE_FOR_PICK_UP, DELIVERED_INDIRECT_SIGNATURE


//    Label Created: The shipping label has been generated but the package hasn't been picked up yet.
//    Order Placed: The order has been placed and is awaiting fulfillment.
//    Shipment Pending: The shipment details are registered but the package hasn't been received by the carrier.

//    Picked Up: The package has been picked up by the carrier from the sender's location.
//    In Transit: The package is on its way to the recipient's location.
//    At Transit Hub: The package is at a sorting or distribution center.Out for Delivery: The package is on the delivery vehicle and scheduled for delivery on that day.

//    Delivered: The package has been delivered to the recipient or a designated location.
//    Delivery Attempted: The carrier attempted to deliver the package but was unsuccessful (e.g., recipient not home, incorrect address).
//    Delivered - Held at Location: The package is being held at a carrier facility or pickup location for the recipient to collect.

//    Shipment Exception: There is an issue with the shipment, such as a damaged package, incorrect address, or customs clearance problems.
//    Return to Sender: The shipment is being returned to the sender due to an issue (e.g., refused by recipient, undeliverable).
//    Lost: The package is lost and cannot be located.

//    Manifest Created: The shipment information has been included in the carrier's manifest for tracking purposes.
//    Available for Pickup: The package is ready for pickup by the recipient at a designated location.
//    Delivered - Indirect Signature: The package was delivered without a direct signature from the recipient (e.g., left at the door).

}
