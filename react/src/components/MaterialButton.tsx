import {Button} from "@material-ui/core";
import React from "react";

export interface MaterialButtonProps {
    onPress: () => any;
		loading: boolean;
}

const MaterialButton = ({onPress, loading}: MaterialButtonProps) => (
    <Button variant={"contained"} color={"primary"} disabled={loading} onClick={onPress} className="button">Wykonaj</Button>);

export default MaterialButton;
