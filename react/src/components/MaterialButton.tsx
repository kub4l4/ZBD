import {Button} from "@material-ui/core";
import React from "react";

export interface MaterialButtonProps {
    onPress: () => any;
}

const MaterialButton = ({onPress}: MaterialButtonProps) => (
    <Button variant={"contained"} color={"primary"} onClick={onPress} className="button">Wykonaj</Button>);

export default MaterialButton;